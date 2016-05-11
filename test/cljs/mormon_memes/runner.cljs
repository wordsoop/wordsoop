(ns mormon-memes.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [mormon-memes.core-test]))

(doo-tests 'mormon-memes.core-test)
