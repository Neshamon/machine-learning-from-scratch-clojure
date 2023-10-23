(ns ml-from-scratch.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def mother-height 165)

(def father-height 185)

(defn child-height-regression
  "This function performs basic linear regression on a the heights
  of a child's parents with the given weights. It will return a string
  which is the predicted height of the child"
  [weights parent-height]
  (apply str (concat (str (+ (first weights)
                             (* (second weights) (first parent-height))
                             (* (last weights) (last parent-height)))) " cm")))

(child-height-regression [34 0.39 0.33] [mother-height father-height]) ;; => 159.4 cm
