import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Categorias } from '../categorias';

@Component({
  selector: 'app-editar-curso',
  templateUrl: './editar-curso.component.html',
  styleUrls: ['./editar-curso.component.css']
})
export class EditarCursoComponent implements OnInit {
  
  constructor(private httpCliente: HttpClient,private activatedRoute: ActivatedRoute) { }
  //atributo
  mensagem: string = '';

  formEdicao!: FormGroup;

  categoria!: Categorias[]


  //Função é executada quando a página é aberta
  ngOnInit(): void {
    //capturar o id enviado pela URL
    const idCurso = this.activatedRoute.snapshot.paramMap.get('id') as string;

    //montando a estrutura do formulário
    this.formEdicao = new FormGroup({

      //campos do formulário...serão os mesmos que temos na consulta

      idCurso: new FormControl(''),
      descricao: new FormControl('', [Validators.required]),
      dataInicio: new FormControl('', [Validators.required]),
      dataTermino: new FormControl('', [Validators.required]),
      quantidade: new FormControl('', [Validators.required]),
      categoria: new FormControl('', [Validators.required])

    });

    this.carregarCategorias();

    //consultar o curso na API através do id
    this.httpCliente.get(environment.apiUrl+'/get/' + idCurso).subscribe(

      (data: any) => {

         this.formEdicao.patchValue(data);
        



      },
      (e) => {
        this.mensagem = e.error 
      }
    )

  }

  //carregar categorias
  carregarCategorias() {
    //buscar categorias do banco

    this.categoria = [
      {idCategoria: 1,categoria:'Multiplataforma'},
      {idCategoria: 2,categoria:'Banco de dados'},
      {idCategoria: 3,categoria:'Metodologia'},
      {idCategoria: 4,categoria:'Comportamento'},
      {idCategoria: 5,categoria:'Comunicação'}
    ];

  }

  get form(): any {

    return this.formEdicao.controls;

  }

  //função para fazer a camada do edição na API
  onSubmit(): void {

    let curso = this.formEdicao.value//atribuindo o valor da variavel


    this.httpCliente.put(environment.apiUrl + '/put',
      curso, { responseType: 'text' }).subscribe(
        data => {
       
          this.mensagem = "Curso editado com sucesso";
          this.formEdicao.reset();
        },
        e => {
      
          this.mensagem = e.error;
        }
      )
  }

}
