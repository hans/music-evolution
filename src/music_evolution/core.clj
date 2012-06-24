(ns music-evolution.core
  (:use [overtone.core]))

(definst sin-key [freq 200] (sin-osc freq))

(defn avg [& nums]
  {:pre [(> (count nums) 0)]}
  (/ (reduce + nums)
     (count nums)))

(defn make-chord
  ([] (make-chord 3 0 6000))
  ([num min max]
     (take num
           (repeatedly #(- (rand-int (+ max min)) min)))))

(defn play-chord
  ([] (play-chord (make-chord)))
  ([chord]
     (map sin-key chord)))

(defn mutate-cps
  "Mutate a cps value."

  ([cps] (mutate-cps cps 50))
  ([cps max-delta]
     (+ cps (- max-delta (rand-int (* 2 max-delta))))))

(defn mutate-chord
  [chord]
  (map mutate-cps chord))

(defn mutate-and-replay-chord
  [chord]

  (let [new-chord (mutate-chord chord)]
    (play-chord new-chord)
    new-chord))

(defn mate-chords
  [& chords]
  (apply (partial map avg) chords))