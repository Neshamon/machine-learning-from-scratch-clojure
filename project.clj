(defproject ml-from-scratch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.mentat/emmy-viewers "0.3.1"]
                 [io.github.nextjournal/clerk "0.15.957"]
                 [com.taoensso/timbre "6.3.1"]]
  :main ^:skip-aot ml-from-scratch.core/lin-reg
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
