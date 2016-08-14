(ns clojure-doc-web-scraper.core
  (:require [clojure-doc-web-scraper.scraper :as scraper] :reload
            [clojure-doc-web-scraper.defaults :as defaults] :reload)
  (:gen-class))


(defn -main
  "Runs the scraper"
  []
  (let [functions (-> (scraper/get-dom (str  defaults/clojure-docs-url "/clojure.core"))
                       (scraper/find-functions)
                       (scraper/extract-functions)
                       (first)
                       (list)
                       (scraper/add-func-info))]
    (first  functions)))
