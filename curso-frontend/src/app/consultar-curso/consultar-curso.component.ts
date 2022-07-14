import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-curso',
  templateUrl: './consultar-curso.component.html',
  styleUrls: ['./consultar-curso.component.css']
})
export class ConsultarCursoComponent implements OnInit {
  curso: any[] = [];

  mensagem: string = '';
  formConsulta!: FormGroup;
  constructor(
    private httpClient: HttpClient,

  ) { }

  //método executado quando o componente é aberto
  ngOnInit(): void {

    this.formConsulta = new FormGroup({
      //campos formulario
      descricao: new FormControl('', [Validators.required]),
      dataInicio: new FormControl('', [Validators.required]),
      dataTermino: new FormControl('', [Validators.required]),
      quantidade: new FormControl('', [Validators.required]),
      categoria: new FormControl('', [Validators.required])

    })

    this.httpClient.get(environment.apiUrl + '/getAll?descricao=' + this.formConsulta.value.descricao + "&dataInicio=" + this.formConsulta.value.dataInicio + "&dataTermino=" + this.formConsulta.value.dataTermino)
      .subscribe(
        (data) => {
          this.curso = data as any[];
          console.log(data)
        },
        (e) => {
          console.log(e);
        }
      )
  }

  //função para fazer a exclusão do produto na API
  excluir(idCurso: number): void {

    if (window.confirm('Deseja realmente excluir o produto selecionado?')) {

      this.httpClient.delete(environment.apiUrl + "/delete/" + idCurso,
        { responseType: 'text' })
        .subscribe(
          (data) => {
            alert(data);
            this.ngOnInit();
          },
          (e) => {
            this.mensagem=e.error
          }
        )
    }
  }

  formPeriodo = new FormGroup({

    descricao: new FormControl(''),
    dataInicio: new FormControl(''),
    dataTermino: new FormControl(''),
  })

  get form(): any {
    return this.formPeriodo.controls;
  }

  onSubmit(): void {

    this.httpClient.get(environment.apiUrl + "/getAll?descricao=" + this.formPeriodo.value.descricao + "&dataInicio=" + this.formPeriodo.value.dataInicio + "&dataTermino=" + this.formPeriodo.value.dataTermino).subscribe(
      (data) => { this.curso = data as any[]; },
      (e) => {
        
      },
    )
  }

  limpar(){
    this.formPeriodo.reset();
    this.ngOnInit();

  }

  
}
