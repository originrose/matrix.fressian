(defproject thinktopic/matrix.fressian "0.1.0"
  :description "Adds Fressian support for core.matrix."
  :url "http://github.com/rosejn/matrix.fressian"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [net.mikera/core.matrix "0.32.1"]
                 [org.clojure/data.fressian "0.2.0"]]

  :profiles {:dev {:dependencies [[net.mikera/vectorz-clj "0.28.0"]]}})
