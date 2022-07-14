import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastrarCursoComponent } from './cadastrar-curso/cadastrar-curso.component';
import { EditarCursoComponent } from './editar-curso/editar-curso.component';
import { ConsultarCursoComponent } from './consultar-curso/consultar-curso.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes= [

  {path: 'cadastrar-curso', component: CadastrarCursoComponent},
  {path: 'consultar-curso', component: ConsultarCursoComponent},
  {path: 'editar-curso/:id', component: EditarCursoComponent}
  
]
@NgModule({
  declarations: [
    AppComponent,
    CadastrarCursoComponent,
    EditarCursoComponent,
    ConsultarCursoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
