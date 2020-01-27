var chart = new Chartist.Line('#lineB-ChartExample', {
  labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
  series: [
    [5, 5, 10, 8, 7, 5, 4, null, null, null, 10, 10, 7, 8, 6, 9],
    [10, 15, null, 12, null, 10, 12, 15, null, null, 12, null, 14, null, null, null]
    [{x:3, y: 3},{x: 4, y: 3}, {x: 5, y: undefined}, {x: 6, y: 4}, {x: 7, y: null}, {x: 8, y: 4}, {x: 9, y: 4}]
  ]
}, {
  fullWidth: true,
  lineSmooth: Chartist.Interpolation.cardinal({
    fillHoles: true,
  }),
  low: 0
});

var data = {
  labels: ['G', 'F', 'M', 'A', 'M', 'G', 'L', 'A', 'S', 'O', 'N', 'D'],
  series: [
    [5, 4, 3, 7, 5, 10, 3, 4, 8, 10, 6, 8]
  ]
};

var options = {
  seriesBarDistance: 10
};

var responsiveOptions = [
  ['screen and (max-width: 640px)', {
    seriesBarDistance: 5,
    axisX: {
      labelInterpolationFnc: function (value) {
        return value[0];
      }
    }
  }]
];

new Chartist.Bar('#lineBarExample', data, options, responsiveOptions);

var data = {
  series: [5, 3, 4]
};

var sum = function(a, b) { return a + b };

new Chartist.Pie('#pieChartExample', data, {
  labelInterpolationFnc: function(value) {
    return Math.round(value / data.series.reduce(sum) * 100) + '%';
  }
});


const mobileToggle = document.querySelector('.navbar-brand')
const backDrop = document.querySelector('.backdrop')

mobileToggle.addEventListener('click', (e) => {
  e.preventDefault()
  backDrop.style.display = 'block'
  document.querySelector('.sidebar')
  .classList.toggle('open')
})

backDrop.addEventListener( 'click' ,function(e) {
  
    document.querySelector('.sidebar')
  .classList.toggle('open')
  
  setTimeout(() => this.style.display = "none" , 200)
})