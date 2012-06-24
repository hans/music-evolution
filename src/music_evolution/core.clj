(ns music-evolution.core
  (:use [overtone.core]))

(definst sin-key [freq 200] (sin-osc freq))

(defn make-chord
  ([] (make-chord 3 0 6000))
  ([num min max]
     (take num
           (repeatedly #(- (rand-int (+ max min)) min)))))

(defn play-chord
  ([] (play-chord (make-chord)))
  ([chord]
     (map sin-key chord)))