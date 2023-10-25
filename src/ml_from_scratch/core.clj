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

(defn basic-regression
  "This function is a more abstract version of child-height regression.
  It takes weights, inputs, and an optional unit and then returns
  a prediction based off of the data.

  Merged bias is a variable that makes it easier to calculate what
  the prediction may be by allowing the first weight to remain itself
  while the mapping function is applied to the rest of the data.
  It does this by adding a 1 to the beginning of the input data structure
  and coerces it to a vector.

  Merged bias is then used in the place of inputs when calculating
  the prediction. This calcuation is done by multiplying each value
  in the sequences weights and merged bias, and then is summed up by
  the reducing function to emulate the function:
  f(x,w) = w0 * x0 + w1 * x1 + ... + wn * xn
  "
  [weights inputs & unit]
  (let [merged-bias (vec (cons 1 inputs))]
    (if unit
      (apply str (concat (str (reduce + (map #(* %1 %2) weights merged-bias))) unit))
      (reduce + (map #(* %1 %2) weights merged-bias)))))

(child-height-regression [34 0.39 0.33] [mother-height father-height]) ;; => 159.4 cm

(basic-regression [34 0.39 0.33] [mother-height father-height] " cm") ;; => 159.4 cm

(defn least-square-loss [weights inputs prediction]
  (/ (reduce + (reduce * (repeat 2 (- prediction (map #(* %1 %2) weights (basis-function inputs)))))) 2))

(defn basis-function [input operation dimensions]
  ())
