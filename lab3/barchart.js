var margin = 200;
var svg = d3.select("svg");
var width = svg.attr("width") - margin;
var height = svg.attr("height") - margin;

svg.append("text")
    .attr("transform", "translate(100,0)")
    .attr("x", 0)
    .attr("y", 50)
    .attr("class", "title")
    .text("Прирост населения в Казахстане (на 1000)");
var xScale = d3.scaleBand().range([0, width]).padding(0.4);
var yScale = d3.scaleLinear().range([height, 0]);

var gElement = svg.append("g");
gElement.attr("transform", "translate(100,100)");

var data = [
    { year: 2010, growth: 13.6, births: 367752 ,deceased: 145875},
    { year: 2011, growth: 13.8, births: 372544 ,deceased: 144213},
    { year: 2012, growth: 14.1, births: 379121 ,deceased: 141220},
    { year: 2013, growth: 14.7, births: 393421 ,deceased: 137630},
    { year: 2014, growth: 15.5, births: 401066 ,deceased: 132236},
    { year: 2016, growth: 15.6, births: 400640 ,deceased: 131373},
    { year: 2017, growth: 14.4, births: 390520 ,deceased: 130033},
    { year: 2019, growth: 14.6, births: 403064 ,deceased: 133489},
];

xScale.domain(data.map(function(d) { return d.year; }));
yScale.domain([0, d3.max(data, function(d) { return d.growth; })]);

gElement.append("g").attr("transform", "translate(0," + height + ")")
    .call(d3.axisBottom(xScale));

gElement.append("g").call(d3.axisLeft(yScale));
var defaultText = "Более подробную информацию можно с наведением курсора мыши на столбец"

function onMouseOver(d, i) {
    d3.select(this)
        .attr('class', 'highlight');

    d3.select('.info')
        .text(i.year + " г. количество родившихся в стране " + i.births + "человек, кол-во умерших - " + i.deceased + "человек");

    d3.select(this)
        .transition()
        .duration(500)
        .attr('width', xScale.bandwidth() + 5)
        .attr("y", (d) => yScale(d.growth) - 10)
        .attr("height", (d) => height - yScale(d.growth) + 10);

}

function onMouseOut(d, i) {
    d3.select(this)
        .attr('class', 'bar');

    d3.select('.info')
        .text(defaultText);

    d3.select(this)
        .transition()
        .duration(550)
        .attr('width', xScale.bandwidth())
        .attr("y", (d) => yScale(d.growth))
        .attr("height", (d) => height - yScale(d.growth));
}

gElement.selectAll(".bar")
    .data(data)
    .enter()
    .append("rect")
    .attr("class", "bar")
    .on("mouseover", onMouseOver)
    .on("mouseout", onMouseOut)
    .attr("x", (d) => xScale(d.year))
    .attr("y", (d) => yScale(d.growth))
    .attr("width", xScale.bandwidth())
    .transition()
    .ease(d3.easeLinear)
    .duration(550)
    .delay((d, i) => i * 50)
    .attr("height", (d) => height - yScale(d.growth));

svg.append("text").attr("x", 100).attr("y", 500).attr("class", "info").text(defaultText);
