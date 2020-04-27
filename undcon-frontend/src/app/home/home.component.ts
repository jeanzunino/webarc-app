import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  public chartTypeBar: string = 'bar';

  public chartTypeDoughnut: string = 'doughnut';

  public chartTypePie: string = 'pie';

  public chartDatasetsStock: Array<any> = [
    { data: [65, 70, 100, 20, 56, 55, 40], label: 'Estoque Mínimo' },
    { data: [30, 60, 90, 5, 36, 45, 0], label: 'Estoque Atual' }
  ];

  public chartDatasetsProdSales: Array<any> = [
    { data: [200, 100, 100, 200, 560, 505], label: 'Produtos Vendidos' }
  ];

  public chartDatasetsCustomers: Array<any> = [
    { data: [120] }
  ];

  public chartDatasetsProviders: Array<any> = [
    { data: [230] }
  ];

  public chartLabels: Array<any> = ['Skol lt 350ml', 'Coca-cola 2L', 'Kaiser lt 350ml', 'Arroz 5kg', 'Trigo', 'Subzero lt 350ml'];

  public chartLabelsPie: Array<any> = ['Teste'];

  public chartColors: Array<any> = [
    {
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)'
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)'
      ],
      borderWidth: 2,
    },
    {
      backgroundColor: [
        'rgba(54, 162, 235, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(54, 162, 235, 0.2)'
      ],
      borderColor: [
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)'
      ],
      borderWidth: 2,
    }
  ];

  public chartColorsPie: Array<any> = [
    {
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)'
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)'
      ],
      borderWidth: 2,
    }
  ];
  private chartOptions: any = {
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    title: {
      display: true,
      text: '',
      fontSize: 30,
      fontStyle: 'bold'
    }
  };

  public getChartOptionsBar(chartTitle: string){
    this.chartOptions.title.text = chartTitle;
    return this.chartOptions;
  }

  public getChartOptionsPie(chartTitle: string){
    this.chartOptionsPie.title.text = chartTitle;
    return this.chartOptionsPie;
  }

  private chartOptionsPie: any = {
    responsive: true,
    rotation: 4,
    circumference: 1,
    cutoutPercentage: 0,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    pieceLabel: {
      mode: 'value',
      position: 'outside',
      fontColor: '#FFFFFF',
      indexLabel: "{label} - #percent%",
		  toolTipContent: "<b>{label}:</b> {y} (#percent%)",
      format: function(value) {
        return '$' + value;
      }
    },
    title: {
      display: true,
      text: '',
      fontSize: 30,
      fontStyle: 'bold'
    },
    legend: {
      position: 'bottom',
      labels: {
        pointStyle: 'circle',
        usePointStyle: true
      }
    }
  };

  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }

  constructor(public spinner: NgxSpinnerService) {

  }

  ngOnInit() {
    this.spinner.hide();
  }
}
