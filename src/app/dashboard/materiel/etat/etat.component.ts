import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Marque} from '../../../models/marque';
import {Etat} from '../../../models/etat';
import {MarqueService} from '../../../services/dashboard/marque.service';
import {EtatService} from '../../../services/dashboard/etat.service';
import {Magasin} from '../../../models/magasin';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-etat',
  templateUrl: './etat.component.html',
  styleUrls: ['./etat.component.css']
})
export class EtatComponent implements OnInit {

  validateEtatForm!: FormGroup;

  etatList: Etat[];

  indexOfTab: number;

  listOfColumn: any = [];

  constructor(
    private fb: FormBuilder,
    private etatService: EtatService,
    private behaviorService: BehaviorService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Matériel', 'Etat']);

    this.makeEtatForm(null);

    this.list();

    this.listOfColumnHeadeer();

  }

  makeEtatForm(etat: Etat){
    this.validateEtatForm = this.fb.group({
      id: [etat != null ? etat.id : null],
      libelle: [etat != null ? etat.libelle : null,
        [Validators.required]],
    });
  }

  resetEtatForm(e: MouseEvent): void {
    e.preventDefault();
    this.validateEtatForm.reset();
    for (const key in this.validateEtatForm.controls) {
      this.validateEtatForm.controls[key].markAsPristine();
      this.validateEtatForm.controls[key].updateValueAndValidity();
    }
    this.makeEtatForm(null);
    console.log('Tab en cours ==> ', this.indexOfTab);
  }

  updateForm(data: Etat){

    this.makeEtatForm(data);

    this.indexOfTab = 1;
  }


  list(): void {
    this.etatService.getList().subscribe(
      (data: any) => {
        this.etatList = data;
        console.log('EtatList ==>', this.etatList);
      },
      (error: HttpErrorResponse) => {
        console.log('error getList Etat ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  confirmMsgDelete(data: Etat){

    this.etatService.deleteEtat(data.id).subscribe(
      (data01: any) => {
        console.log('data du delete ==>', data01);
        //this.indexOfTab = 0;
        //this.nzMessageService.info('click cancel');
        this.list();
      },
      (error: HttpErrorResponse) => {
        console.log('error deleteEtat ==>', error.message, ' ', error.status, ' ', error.statusText);
      }
    );
  }


  cancelMsgDelete(): void {
    //this.nzMessageService.info('click confirm');
  }

  submitEtatForm(): void {
    for (const i in this.validateEtatForm.controls) {
      this.validateEtatForm.controls[i].markAsDirty();
      this.validateEtatForm.controls[i].updateValueAndValidity();
    }

    if (this.validateEtatForm.valid) {

      const formData = this.validateEtatForm.value;
      if (formData.id == null) {
        this.etatService.createEtat(formData).subscribe(
          (data: any) => {
            this.etatList.unshift(data);
            this.etatList = [...this.etatList];
            this.makeEtatForm(null);

            console.log('Enregistrement ok');
            this.indexOfTab = 0;

          },
          (error: HttpErrorResponse) => {
            console.log('Enregistrement non ok');

          });
      } else {
        const i = this.etatList.findIndex(p => p.id == formData.id);
        this.etatService.updateEtat(formData).subscribe(
          (data: any) => {
            this.etatList[i] = data;
            this.etatList = [...this.etatList];
            this.makeEtatForm(null);

            console.log('Update ok');
            this.indexOfTab = 0;

          },
          (error: HttpErrorResponse) => {
            console.log('Update non ok');
          });
      }

    }
    else {

    }

  }

  listOfColumnHeadeer(){
    this.listOfColumn = [

      {
        title: 'Libellé',
        compare: null,
        priority: false
      },

    ];
  }

}



