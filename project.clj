(defproject thinktopic/matrix.fressian "0.3.0"
  :description "Adds Fressian support for core.matrix."
  :url "http://github.com/rosejn/matrix.fressian"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [net.mikera/core.matrix "0.49.0"]
                 [thinktopic/aljabr "0.1.0-SNAPSHOT"]
                 [org.clojure/data.fressian "0.2.1"]
                 [net.unit8/fressian-cljs "0.2.0"]]

  :profiles {:dev {:dependencies [[net.mikera/vectorz-clj "0.37.0"]]}}

  :lein-release {:deploy-via :clojars}
)
