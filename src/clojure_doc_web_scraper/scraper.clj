(ns clojure-doc-web-scraper.scraper
  (:require [clojure-doc-web-scraper.defaults :as defaults]
            [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]))

(defn get-dom
  "Gets a DOM like object from ClojureDocs"
  [url]
  (let [{:keys [body error]} 
        @(http/get url)]
    (if error
      (do (println (str "Unable to request page for " url))
          nil)
      (html/html-snippet body))))

(defn find-functions
  "Takes the ClojarDocs DOM object and returns a list 
  of html elements containing the functions from clojure.core"
  [doc-dom]
  (html/select doc-dom [:dt.name]))

(defn return-function-map
  "Returns a map containing function name and url"
  [func-elem]
  (let [func-name ((comp first :content) func-elem)
        func-url  ((comp :href :attrs) func-elem)]
    {:name func-name :url func-url}))

(defn extract-functions
  "Takes list of html elements and extracts function 
  name and url. Returns information in a map for every
  function element"
  [func-lst]
  (map (comp return-function-map first :content) func-lst))

(defn create-map-func-info
  "Extracts function arg lists and docstrings,
  returns a map for each function"
  [args docstring]
  (let [arg-list (map (comp first :content) args)
        docs ((comp first :content first) docstring)]
    {:args arg-list :docstring docs}))

(defn find-function-info
  "Takes the function page DOM and returns a map 
  containing the functions information"
  [func-dom]
  (let [args (html/select func-dom [:li.arglist])
        docstring (html/select func-dom [:div.docstring :> :pre])]
    (create-map-func-info args docstring)))

(defn get-func-info
  "Sends a get request for info from ClojareDocs"
  [func-url]
  ;; Slow down the requests, so that we don't
  ;; get banned from the site
  (Thread/sleep 1000)
  (-> (get-dom (str defaults/clojure-docs-url func-url))
      (find-function-info)))

(defn add-func-info
  "For each function, adds the docstring
  and argument lists"
  [func-lst]
  (map #(merge % (get-func-info (:url %))) func-lst))
