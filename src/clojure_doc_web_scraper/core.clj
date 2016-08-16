(ns clojure-doc-web-scraper.core
  (:require [clojure-doc-web-scraper.scraper :as scraper] :reload
            [clojure-doc-web-scraper.defaults :as defaults] :reload
            [clojure.string :as string]
            [clojure.java.io :as io])
  (:gen-class))

(defn format-output
  "Formats each function for output"
  [func]
  (println func)
  (let [name      (:name func)
        url       (:url func)
        args      (into [] (:args func)) ;; Convert to non-lazy list to print
        docstring ((fnil string/replace "") (:docstring func) #"\n" " ")]
    (str "name: " name " url: " url " args: " args " docstring: " docstring "\n")))

(defn -main
  "Runs the scraper and saves them to file path
  provided as the first argument"
  [& args]
  (let [functions (-> (scraper/get-dom 
                       (str defaults/clojure-docs-url "/clojure.core"))
                      (scraper/find-functions)
                      (scraper/extract-functions)
                      (scraper/add-func-info))
        w (io/writer (first args) :append true)]
    ;; Write the contents of functions to a file
    (doseq [x functions] (.write w (format-output x)))
    (.close w)))
