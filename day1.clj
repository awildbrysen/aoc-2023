(ns day1
  (:require [clojure.string :as str]))

(defn parse-int [n]
  (if (integer? n) n
      (try (Integer/parseInt n)
           (catch Exception _ nil))))

(defn calibration-value [s]
  (let [digits (->> (str/split s #"")
                    (map #(parse-int %))
                    (filter #(some? %))
                    (map str))]
    (reduce str [(first digits) (last digits)])))

(defn count-calibration-values [list]
  (->> list
       (map calibration-value)
       (map parse-int)
       (filter some?)
       (reduce +)))

(comment
  (count-calibration-values ["1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"])
  (count-calibration-values (str/split (slurp "day1.txt") #"\r\n")))