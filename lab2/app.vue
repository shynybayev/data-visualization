Vue.component('bar-chart', {
	extends: VueChartJs.Bar,
	data: function () {
		return {
			datacollection: {
				labels: ['Kazakhstan', 'Uzbekistan', 'Ukraine', 'Kyrgyzstan'],
				datasets: [
					{
						label: 'Data One',
						backgroundColor: '#f87979',
						pointBackgroundColor: 'white',
						borderWidth: 1,
						pointBorderColor: '#249EBF',
						data: [100, 70, 30, 20]
					}
]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						},
						gridLines: {
							display: true
						}
					}],
					xAxes: [{
						ticks: {
							beginAtZero: true
						},
						gridLines: {
							display: false
						}
					}]
				},
				legend: {
					display: false
				},
				tooltips: {
					enabled: true,
					mode: 'single',
					callbacks: {
						label: function(tooltipItems, data) {
							return '$' + tooltipItems.yLabel;
						}
					}
				},
				responsive: true,
				maintainAspectRatio: false,
				height: 200
}
		}
	},
	mounted () {
		this.renderChart(this.datacollection, this.options)
	}
})

var vm = new Vue({ 
	el: '.app',
})
