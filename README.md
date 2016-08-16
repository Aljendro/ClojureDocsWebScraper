# clojure-doc-web-scraper

ClojureDocWebScraper is intended to be used to scrape the Clojuredocs website.
The focus of this script was to scrape the functions, their definitions, and
the usage of the functions. All this information would then be saved to
an out-file.

# Usage

This program was intended to run as a script. After downloading the source
from github.

     cd ClojureDocsWebScraper
     lein repl       ;; Assuming you have leinigen installed

Once the REPL prompt appears you can run:

     ;; Will run for about 700s, purposedly slow to avoid taxing ClojureDocs site
     (-main [path/to/out/file/on/disk])    ;; Will append function info to file

# How to use the out-file

From here the out-file will contain all the function information (one function per line).
I have also written a python script located in my Anki fork, which reads the
file created and creates a flashcard for each function.

     # After downloading my repo
     python3 [/path/to/exporting_to_file.py] [/path/to/out/file] [/path/to/.apkg/file] 

Distributed under the Eclipse Public License either version 1.0
