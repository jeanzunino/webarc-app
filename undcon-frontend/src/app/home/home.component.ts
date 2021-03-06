import { Component, OnInit } from '@angular/core';
import { IntervalType } from '@app/core/enum/interval-type';
import { ItemDashboardInfoDto } from '@app/core/service/dtos/item-dashboard-info-dto';
import { DashBoardService } from '@app/core/service/dashboard/dashboard.service';
import { ValueByInterval } from '@app/core/service/dtos/value-by-interval';
import { NgxSpinnerService } from 'ngx-spinner';
import { Product } from '@app/core/model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit{
  public chartTypeBar: string = 'bar';

  public chartTypeDoughnut: string = 'doughnut';

  public chartTypePie: string = 'pie';

  public chartTypeLine: string = 'line';

  constructor(
    public spinner: NgxSpinnerService,
    public service: DashBoardService
  ) {

  }

  public chartDatasetsStock: Array<any> = [{ data: [0]}];
  public chartDatasetsStockLabels: Array<any> = [];

  public chartDatasetsCustomers: Array<any> = [{ data: [0]}];

  public chartDatasetsProviders: Array<any> = [{ data: [0] }];

  public chartDatasetsProducts: Array<any>= [{ data: [0] }];

  public chartDatasetsTotalSaledProduct: Array<any> = [{ data: [0] }];
  public chartDatasetsTotalSaledProductLabels: Array<any> = [];

  public chartDatasetsTotalSaledService: Array<any> = [{ data: [0] }];
  public chartDatasetsTotalSaledServiceLabels: Array<any> = [];

  public chartDatasetsTotalIncome: Array<any> = [{ data: [0] }];
  public chartDatasetsTotalIncomeLabels: Array<any> = [];

  public chartDatasetsTotalExpense: Array<any> = [{ data: [0] }];
  public chartDatasetsTotalExpenseLabels: Array<any> = [];

  public chartDatasetsTopSaledProductRS: Array<any> = [{ data: [0] }];
  public chartDatasetsTopSaledProductQtde: Array<any> = [{ data: [0] }];
  public chartDatasetsTopSaledProductLabels: Array<any> = [];

  public chartColors: Array<any> = [
    {
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
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
        'rgba(54, 162, 235, 0.2)',
      ],
      borderColor: [
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(54, 162, 235, 1)',
      ],
      borderWidth: 2,
    },
  ];

  public chartColorsPie: Array<any> = [
    {
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 99, 132, 0.2)',
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
        'rgba(255, 99, 132, 1)',
      ],
      borderWidth: 2,
    },
  ];
  private chartOptions: any = {
    responsive: true,
    scales: {
      yAxes: [
        {
          ticks: {
            fontColor: "white",
            fontSize: 18,
            stepSize: 1,
            beginAtZero: false
          }
        },
      ],
      xAxes: [
        {
          ticks: {
              fontColor: "white",
              fontSize: 18,
              stepSize: 1,
              beginAtZero: false
          }
        }
      ]
    },
    title: {
      display: true,
      text: '',
      fontSize: 30,
      fontStyle: 'bold',
    },
    legend: {
      display: true,
      labels: {
          fontColor: 'white',
          fontSize: 14
      }
    }
  };

  ngOnInit() {
    this.loadData();
  }

  loadData(){
    this.service.getCountCustomersTotal().toPromise().then((result) => {
      this.chartDatasetsCustomers= [{ data: [result]}];
    });

    this.service.getCountProductsTotal().toPromise().then((result) => {
      this.chartDatasetsProducts = [{ data: [result]}];
    });

    this.service.getCountProvidersTotal().toPromise().then((result) => {
      this.chartDatasetsProviders = [{ data: [result]}];
    });

    this.service.getProductsStockMin().toPromise().then((result: Product[]) => {
      let dataArray1 = new Array();
      let dataArray2 = new Array();
      let labels = new Array();
      result.forEach((value: Product) => {
        if (value) {
          dataArray1.push(value.stock);
          dataArray2.push(value.stockMin);
          labels.push(value.name);
        }
      });
      this.chartDatasetsStock = [ { data: dataArray1, label: 'Saldo Estoque'},
                                  { data: dataArray2, label: 'Estoque Mínimo'}];
      this.chartDatasetsStockLabels = labels;
    });

    let startDate = "2020-01-01T10:15:30.00Z";
    let endDate = "2021-01-24T10:15:30.00Z";
    let type = IntervalType.MONTHLY;
    this.service.getTotalSaledProductByInterval(startDate, endDate, type).toPromise().then((result: ValueByInterval[]) => {
      let dataArray = new Array();
      let labels = new Array();
      result.forEach((value: ValueByInterval) => {
        if (value) {
          dataArray.push(value.value);
          labels.push(this.getDate(value.interval));
        }
      });
      this.chartDatasetsTotalSaledProduct = [{ data: dataArray, label: 'Produtos'}];
      this.chartDatasetsTotalSaledProductLabels = labels;
    });

    this.service.getTotalSaledServiceByInterval(startDate, endDate, type).toPromise().then((result: ValueByInterval[]) => {
      let dataArray = new Array();
      let labels = new Array();
      result.forEach((value: ValueByInterval) => {
        if (value) {
          dataArray.push(value.value);
          labels.push(this.getDate(value.interval));
        }
      });
      this.chartDatasetsTotalSaledService = [{ data: dataArray, label: 'Serviços'}];
      this.chartDatasetsTotalSaledServiceLabels = labels;
    });

    this.service.getTotalIncomeByInterval(startDate, endDate, type).toPromise().then((result: ValueByInterval[]) => {
      let dataArray = new Array();
      let labels = new Array();
      result.forEach((value: ValueByInterval) => {
        if (value) {
          dataArray.push(value.value);
          labels.push(this.getDate(value.interval));
        }
      });
      this.chartDatasetsTotalIncome = [{ data: dataArray, label: 'Receitas'}];
      this.chartDatasetsTotalIncomeLabels = labels;
    });

    this.service.getTotalExpenseByInterval(startDate, endDate, type).toPromise().then((result: ValueByInterval[]) => {
      let dataArray = new Array();
      let labels = new Array();
      result.forEach((value: ValueByInterval) => {
        if (value) {
          dataArray.push(value.value);
          labels.push(this.getDate(value.interval));
        }
      });
      this.chartDatasetsTotalExpense = [{ data: dataArray, label: 'Despesas'}];
      this.chartDatasetsTotalExpenseLabels = labels;
    });

    this.service.getTopProductSaled(startDate, endDate, 10).toPromise().then((result: ItemDashboardInfoDto[]) => {
      let dataArray1 = new Array();
      let dataArray2 = new Array();
      let labels = new Array();
      result.forEach((value: ItemDashboardInfoDto) => {
        if (value) {
          dataArray1.push(value.totalSaled);
          dataArray2.push(value.quantity);
          labels.push(value.productName);
        }
      });
      this.chartDatasetsTopSaledProductRS = [{ data: dataArray1, label: 'Produtos Mais Vendidos (R$)'}];
      this.chartDatasetsTopSaledProductQtde = [{ data: dataArray2, label: 'Produtos Mais Vendidos (Qtde)'}];
      this.chartDatasetsTopSaledProductLabels = labels;
    });
  }

  public getChartOptionsBar(chartTitle: string) {
    this.chartOptions.title.text = chartTitle;
    return this.chartOptions;
  }

  public getChartOptionsPie(chartTitle: string) {
    this.chartOptionsPie.title.text = chartTitle;
    return this.chartOptionsPie;
  }

  public getChartOptionsLine(chartTitle: string) {
    this.chartLineOptions.title.text = chartTitle;
    return this.chartLineOptions;
  }

  public chartLineOptions: any = {
    responsive: true,
    scales: {
      yAxes: [
        {
          ticks: {
            fontColor: "white",
            fontSize: 14,
            stepSize: 1,
            beginAtZero: false
          }
        },
      ],
      xAxes: [
        {
          ticks: {
              fontColor: "white",
              fontSize: 18,
              stepSize: 1,
              beginAtZero: false
          }
        }
      ]
    },
    title: {
      display: true,
      text: '',
      fontSize: 30,
      fontStyle: 'bold',
    },
  };

  private chartOptionsPie: any = {
    responsive: true,
    rotation: 4,
    circumference: 1,
    cutoutPercentage: 0,
    scales: {
      yAxes: [
        {
          ticks: {
            fontColor: "white",
            fontSize: 14,
            stepSize: 1,
            beginAtZero: true
          }
        },
      ],
      xAxes: [
        {
          ticks: {
              fontColor: "white",
              fontSize: 18,
              stepSize: 1,
              beginAtZero: true
          }
        }
      ]
    },
    pieceLabel: {
      mode: 'value',
      position: 'outside',
      fontColor: '#FFFFFF',
      indexLabel: '{label} - #percent%',
      toolTipContent: '<b>{label}:</b> {y} (#percent%)',
      format: function (value) {
        return '$' + value;
      },
    },
    title: {
      display: true,
      text: '',
      fontSize: 30,
      fontStyle: 'bold',
    },
    legend: {
      position: 'bottom',
      labels: {
        pointStyle: 'circle',
        usePointStyle: true,
      },
    },
  };

  public chartClicked(e: any): void {}
  public chartHovered(e: any): void {}

  private getDate(date) {
    const dateFormat = date.replaceAll("'", '');
    const dateSeparate = dateFormat.split('-')
    const year = dateSeparate[0].substring(2, 4);

    switch (dateSeparate[1]) {
      case '01':
        return 'jan/' + year;
      case '02':
        return 'fev/' + year;
      case '03':
        return 'mar/' + year;
      case '04':
        return 'abr/' + year;
      case '05':
        return 'mai/' + year;
      case '06':
        return 'jun/' + year;
      case '07':
        return 'jul/' + year;
      case '08':
        return 'ago/' + year;
      case '09':
        return 'set/' + year;
      case '10':
        return 'out/' + year;
      case '11':
        return 'nov/' + year;
      default:
        return 'dez/' + year;
    }

  }
}
