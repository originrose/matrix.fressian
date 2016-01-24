(defproject thinktopic/matrix.fressian "0.3.0-SNAPSHOT"
  :description "Adds Fressian support for core.matrix."
  :url "http://github.com/rosejn/matrix.fressian"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [net.mikera/core.matrix "0.45.0"]
                 [org.clojure/data.fressian "0.2.1"]
                 [net.unit8/fressian-cljs "0.2.0"]
                 [thi.ng/ndarray "0.3.0"]]

  :lein-release {:deploy-via :clojars}
)
