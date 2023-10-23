(ns ml-from-scratch.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn basic-regression [weights inputs]
  (+ (first weights)
     (* (second weights) (first inputs))
     (* (last weights) (last inputs))))

(basic-regression [34 0.39 0.33] [165 185])
