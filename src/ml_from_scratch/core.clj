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
  f(x,w) = w_0 * x_0 + w_1 * x_1 + ... + w_n * x_n
  "
  [weights inputs & unit]
  (let [merged-bias (vec (cons 1 inputs))]
    (if unit
      (apply str (concat (str (reduce + (map #(* %1 %2) weights merged-bias))) unit))
      (reduce + (map #(* %1 %2) weights merged-bias)))))

(child-height-regression [34 0.39 0.33] [mother-height father-height]) ;; => 159.4 cm

(basic-regression [34 0.39 0.33] [mother-height father-height] " cm") ;; => 159.4 cm

(defn least-square-loss
  "l2 = 1/2 * ((prediction_0 - (weights_0 * basis(inputs_0))) + ...
               (prediction_n - (weights_n * basis(inputs_n))))^2 +
              (lambda / 2) * |weights|^reg-exponent"
  [weights inputs prediction lambda reg-exponent ^fn basis-fn]
  (+ (/ (reduce + (reduce * (repeat 2 (- prediction (map #(* %1 %2) weights (apply basis-fn inputs)))))) 2)
     (ridge-regression lambda reg-exponent weights)))
;; Needs to be updated so the first set of values is mapped over
;; the result of ridge-regression


(defn basis-function
  "The basis function is used for adjusting a line of best
  fit to functions that may produce curves on a chart such
  as sin, cos, log, and others.

  Whatever the basis function may need to be depends on the
  context of the application. But when it is decided,
  the function is applied to each dimension of the inputs"
  [input operation dimensions]
  ())

(defn ridge-regression
  "Ridge regression is a l2 norm regularization.
  Regularization is when penalties are applied to the
  parameters of a model.

  With ridge regression, the larger lambda is the greater
  the penalty is for the affected parameters, which would be
  the weights. The smaller lambda is, the lesser the penalty

  The original formula is:
  (lambda / 2) * |weights|^exponent"
  [lambda exponent weights]
  (map #(float (* (/ lambda 2) %)) (map #(reduce * (repeat exponent (abs %))) weights)))
;; This function returns a list of the penalized weights.
;; This is probably how it's supposed to be, though I'm not yet sure if I need
;; to reduce it to one integer instead of a list.

(defn calculate-lambda
  "lambda = s_0 / beta"
  []
  ())
;; Still trying to understand what the significance of s_0 is
;; Also trying to understand where beta comes from in the l2 equation.
