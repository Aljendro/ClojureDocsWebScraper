# clojure-doc-web-scraper

ClojureDocWebScraper is intended to be used to scrape the Clojuredocs website.
The focus of this script was to scrape the functions, their definitions, and
the usage of the functions. All this information would then be saved to
an out-file.

# Usage
This program was intended to run as a script. After downloading the source
from github.

     cd ClojureDocsWebScraper
     lein repl

Once the REPL prompt appears you can run:

     (-main [path/to/text/file/on/disk])

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
