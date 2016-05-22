# Words Of Our Prophets

*Truth never lost ground by enquiry -William Penn*

there are (sort of) 2 different repos here.

The main one holds the code.
Then there's a gh-pages dir inside it, which only knows of the `gh-pages` branch.

## Main Code

* all of the source code. right now, there's only frontend stuff in `cljs`.


## GH-Pages

* to generated new code
  * `lein clean`
  * `lein cljsbuild once min`
  * move the new `index.html` and `app.js` into the `gh-pages` dir
  * push that up to the `gh-pages` branch
