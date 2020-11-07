var data = [
  {country:"Almaty", gold:10, silver:20},
  {country:"Astana", gold:20, silver:100},
  {country:"Kostanay", gold:200, silver:50},
  {country:"Karagandy", gold:25, silver:80},
  {country:"Taraz", gold:10, silver:200},
  {country:"Semey", gold:150, silver:75},
  {country:"Aktau", gold:10, silver:70},
  {country:"Uralsk", gold:30, silver:150},
  {country:"Taldykorgan", gold:100, silver:15}
]

var svg = d3.select("body")
            .append("svg")
            .attr("width", 250)
            .attr("height", 250)

svg.selectAll("circle")
   .data(data).enter()
   .append("circle")
   .attr("cx", function(d) {return d.gold})
   .attr("cy", function(d) {return d.silver})
   .attr("r", function(d) {
     return Math.sqrt(d.gold*d.gold+d.silver*d.silver)/20
   })
   .attr("fill", function(d) {
     return "rgb("+d.gold+","+d.silver+",0)"
   })

svg.selectAll("text")
  .data(data).enter()
  .append("text")
  .attr("x", function(d) {return d.gold+10})
  .attr("y", function(d) {return d.silver+4})
  .text(function(d) {return d.country})
  .attr("font-size", "10px")
