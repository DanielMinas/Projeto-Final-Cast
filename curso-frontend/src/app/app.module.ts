import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastrarCursoComponent } from './cadastrar-curso/cadastrar-curso.component';
import { EditarCursoComponent } from './editar-curso/editar-curso.component';
import { ConsultarCursoComponent } from './consultar-curso/consultar-curso.component';

@NgModule({
  declarations: [
    AppComponent,
    CadastrarCursoComponent,
    EditarCursoComponent,
    ConsultarCursoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
