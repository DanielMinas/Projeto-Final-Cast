import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-curso',
  templateUrl: './consultar-curso.component.html',
  styleUrls: ['./consultar-curso.component.css']
})
export class ConsultarCursoComponent implements OnInit {
  cursos: any[] = [];


  constructor(
    private httpClient: HttpClient,

  ) { }

  //método executado quando o componente é aberto
  ngOnInit(): void {



    this.httpClient.get(environment.apiUrl + '/get')
      .subscribe(
        (data) => {
          this.cursos = data as any[];
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
            alert(data); //exibir mensagem em uma janela popup
            this.ngOnInit(); //recarregar a consulta de produtos
          },
          (e) => {
            console.log(e);
          }
        )
    }
  }

}
