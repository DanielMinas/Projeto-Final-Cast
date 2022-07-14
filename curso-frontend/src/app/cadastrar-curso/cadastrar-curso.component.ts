import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { Categorias } from '../categorias';

@Component({
  selector: 'app-cadastrar-curso',
  templateUrl: './cadastrar-curso.component.html',
  styleUrls: ['./cadastrar-curso.component.css']
})
export class CadastrarCursoComponent implements OnInit {

  constructor(private httpCliente: HttpClient) { }

  //atributo
  mensagem: string = '';

  formCadastro!: FormGroup; 

  categoria!: Categorias[];

  ngOnInit(): void {

   //estrutura do formulario
    this.formCadastro = new FormGroup ({
  //campos formulario
    descricao: new FormControl('',[Validators.required]),
    dataInicio: new FormControl('',[Validators.required]),
    dataTermino: new FormControl('',[Validators.required]),
    quantidade: new FormControl('',[Validators.required]),
    categoria: new FormControl('',[Validators.required])

  })
  this.carregarCategorias();

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

 //acessando o formulario/pagina HTML pegando dados tela
 get form():any{
  return this.formCadastro.controls;
}

//fazer chamada de cadastro na API
onSubmit(): void{

  let curso = this.formCadastro.value//atribuindo o valor da variavel

  let categoria = {'idCategoria':this.formCadastro.get('categoria')?.value}

  curso.categoria = categoria

  console.log(curso)

  this.httpCliente.post(environment.apiUrl+'/post',
  curso,{responseType: 'text'}).subscribe(
    data =>{
      this.mensagem = "Curso cadastrado com sucesso";
      this.formCadastro.reset();
      window.location.href = "/consultar-curso";
     ;
    },
    e => {
   
      this.mensagem = e.error ;

    }
  )
}


}
