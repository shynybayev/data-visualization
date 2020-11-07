// Массив массивов - содержат по одной точке для каждого первичного массива.

var arrayOfData = [
  [15, 25], [25, 105], [205, 55],
  [30, 85], [10, 205], [155, 80],
  [15, 75], [35, 155], [105, 20]
]

var svg = d3.select("body")
            .append("svg")
            .attr("width", 250)
            .attr("height", 250)

svg.selectAll("circle")
   .data(arrayOfData).enter()
   .append("circle")
   .attr("cx", function(d) {return d[0]})
   .attr("cy", function(d) {return d[1]})
   .attr("r", 4)

//size
.attr("r", function(d) {
  return Math.sqrt(d[0]*d[0]+d[1]*d[1])/20
})

//color
.attr("fill", function(d) {
  return "rgb("+d[0]+","+d[1]+",0)"
})

// In addition to circles construction
svg.selectAll("text")
  .data(data).enter()
  .append("text")
  .attr("x", function(d) {return d[0]+4})
  .attr("y", function(d) {return d[1]+4})
  .text(function(d) {return d[0] + ", " + d[1]})
  .attr("font-size", "10px")
