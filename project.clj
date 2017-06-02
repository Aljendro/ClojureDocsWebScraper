(defproject clojure-doc-web-scraper "0.1.0-SNAPSHOT"
  :description "Clojure docs web scraper to make flashcards with Anki"
  :license {:name "GNU Affero General Public License"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/spec.alpha "0.1.109"]
                 [enlive "1.1.6"]
                 [http-kit "2.1.18"]]
  :main ^:skip-aot clojure-doc-web-scraper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
