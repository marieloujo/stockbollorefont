(window.webpackJsonp=window.webpackJsonp||[]).push([[10],{lyWG:function(e,n,i){"use strict";i.r(n),i.d(n,"UtilisateursModule",function(){return le});var t=i("ofXK"),r=i("tyNb"),s=i("fXoL");let o=(()=>{class e{constructor(){}ngOnInit(){}}return e.\u0275fac=function(n){return new(n||e)},e.\u0275cmp=s.Hb({type:e,selectors:[["app-utilisateurs"]],decls:1,vars:0,template:function(e,n){1&e&&s.Ob(0,"router-outlet")},directives:[r.h],styles:[""]}),e})();var l=i("3Pt+"),c=i("AytR"),a=i("iLIS"),b=i("tk/3"),u=i("QIn1"),h=i("b6Qw");let p=(()=>{class e{constructor(e,n,i){this.http=e,this.Cookie=n,this.router=i,this.url=c.a.backend+"/service"}getList(){return this.http.get(this.url+"/list",this.http_get_request())}createService(e){return this.http.post(this.url+"/creer-service",e,this.http_get_request())}updateService(e){return this.http.put(this.url+"/modifier-service",e,this.http_get_request())}deleteService(e){return this.http.delete(`${this.url}/supprimer-service/${e}`,this.http_get_request())}http_get_request(){return this.checkCredentials(),{headers:new b.d({"Content-Type":"application/json",Accept:"application/json","Access-Control-Allow-Origin":"*",Authorization:"Bearer "+this.getAccessToken().accessToken})}}checkCredentials(){this.Cookie.check("access_token")||this.logout()}logout(){this.Cookie.delete("access_token","./"),this.Cookie.delete("user","./"),this.router.navigate(["/login"])}getAccessToken(){let e=new u.a;return e=JSON.parse(this.Cookie.get("access_token")),e}}return e.\u0275fac=function(n){return new(n||e)(s.Xb(b.b),s.Xb(h.a),s.Xb(r.d))},e.\u0275prov=s.Jb({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();var d=i("DlHu"),z=i("JqCM"),m=i("oyxB"),f=i("rMZv"),S=i("Nqz0"),g=i("PTRe"),v=i("OzZK"),T=i("RwU8"),D=i("C2AL"),O=i("FwiY"),E=i("4xsP"),k=i("KupA"),P=i("ocnv"),C=i("B+r4");function L(e,n){if(1&e){const e=s.Ub();s.Tb(0,"th",15),s.Dc(1),s.Tb(2,"nz-filter-trigger",16),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc().visible=n}),s.Ob(3,"i",17),s.Sb(),s.Sb()}if(2&e){const e=n.$implicit,i=s.dc(),t=s.pc(15);s.jc("nzSortFn",e.sortFn),s.zb(1),s.Fc(" ",e.title," "),s.zb(1),s.jc("nzVisible",i.visible)("nzActive",i.searchValue.length>0)("nzDropdownMenu",t)}}function A(e,n){if(1&e){const e=s.Ub();s.Tb(0,"tr"),s.Tb(1,"td"),s.Dc(2),s.Sb(),s.Tb(3,"td"),s.Tb(4,"nz-space"),s.Tb(5,"nz-space-item"),s.Tb(6,"button",18),s.ac("click",function(){s.rc(e);const i=n.$implicit;return s.dc().updateForm(i)}),s.Ob(7,"i",19),s.Sb(),s.Sb(),s.Tb(8,"nz-space-item"),s.Tb(9,"button",20),s.ac("nzOnConfirm",function(){s.rc(e);const i=n.$implicit;return s.dc().confirmMsgDelete(i)})("nzOnCancel",function(){return s.rc(e),s.dc().cancelMsgDelete()}),s.Ob(10,"i",21),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb()}if(2&e){const e=n.$implicit;s.zb(2),s.Ec(null==e?null:e.libelle)}}function x(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-tab",3),s.Tb(1,"form",22),s.Tb(2,"div",23),s.Tb(3,"div",24),s.Tb(4,"nz-form-item"),s.Tb(5,"nz-form-label",25),s.Dc(6,"Libell\xe9"),s.Sb(),s.Tb(7,"nz-form-control",26),s.Tb(8,"input",27),s.ac("keyup",function(n){return s.rc(e),s.dc().validateServiceForm.patchValue({libelle:n.target.value.toUpperCase()})}),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(9,"div",24),s.Tb(10,"nz-space"),s.Tb(11,"nz-space-item"),s.Tb(12,"button",28),s.ac("click",function(){return s.rc(e),s.dc().submitServiceForm()}),s.Ob(13,"i",29),s.Dc(14," Enregistrer "),s.Sb(),s.Sb(),s.Tb(15,"nz-space-item"),s.Tb(16,"button",30),s.ac("click",function(n){return s.rc(e),s.dc().resetServiceForm(n)}),s.Ob(17,"i",31),s.Dc(18," Annuler "),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb()}if(2&e){const e=s.dc();s.jc("nzTitle","Nouveau service"),s.zb(1),s.jc("formGroup",e.validateServiceForm),s.zb(4),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(5),s.jc("disabled",e.validateServiceForm.invalid)}}let F=(()=>{class e{constructor(e,n,i,t){this.behaviorService=e,this.fb=n,this.serviceBService=i,this.tokenService=t,this.listOfColumn=[],this.searchValue="",this.visible=!1,this.token=t.getAccessToken()}ngOnInit(){this.behaviorService.setBreadcrumbItems(["Accueil","Gestion Utilisateur","Service"]),this.makeServiceForm(null),this.list(),this.listOfColumnHeadeer(),this.is_admin=this.canWrite()}canWrite(){return this.token.roles.indexOf(c.a.ROLE_ADMIN)>-1}makeServiceForm(e){this.validateServiceForm=this.fb.group({id:[null!=e?e.id:null],libelle:[null!=e?e.libelle:null,[l.r.required]]})}resetServiceForm(e){e.preventDefault(),this.validateServiceForm.reset();for(const n in this.validateServiceForm.controls)this.validateServiceForm.controls[n].markAsPristine(),this.validateServiceForm.controls[n].updateValueAndValidity();this.makeServiceForm(null),this.indexOfTab=0,this.pageIndex=1}submitServiceForm(){for(const e in this.validateServiceForm.controls)this.validateServiceForm.controls[e].markAsDirty(),this.validateServiceForm.controls[e].updateValueAndValidity();if(this.validateServiceForm.valid){const e=this.validateServiceForm.value;if(null==e.id)this.serviceBService.createService(e).subscribe(e=>{this.serviceList.unshift(e),this.serviceList=[...this.serviceList],this.listOfDisplayData=[...this.serviceList],this.makeServiceForm(null),console.log("Enregistrement ok"),this.indexOfTab=0,this.pageIndex=1},e=>{console.log("Enregistrement non ok")});else{const n=this.serviceList.findIndex(n=>n.id==e.id);this.serviceBService.updateService(e).subscribe(e=>{console.log(this.serviceList),console.log(e),this.serviceList[n]=e,this.serviceList=[...this.serviceList],this.listOfDisplayData=[...this.serviceList],console.log(this.serviceList),this.makeServiceForm(null),console.log("Update ok"),this.indexOfTab=0,this.pageIndex=1},e=>{console.log("Update non ok")})}}}list(){this.serviceBService.getList().subscribe(e=>{this.serviceList=e,console.log("Service List ==>",this.serviceList),this.listOfDisplayData=[...this.serviceList],this.pageIndex=1},e=>{console.log("error getList Service ==>",e.message," ",e.status," ",e.statusText)})}reset(){this.searchValue="",this.search()}search(){this.visible=!1,this.listOfDisplayData=this.serviceList.filter(e=>-1!==e.libelle.indexOf(this.searchValue))}updateForm(e){this.makeServiceForm(e),this.indexOfTab=1}confirmMsgDelete(e){this.serviceBService.deleteService(e.id).subscribe(e=>{console.log("data du delete ==>",e),this.list()},e=>{console.log("error deleteMagasin ==>",e.message," ",e.status," ",e.statusText)})}cancelMsgDelete(){}listOfColumnHeadeer(){this.listOfColumn=[{title:"Libell\xe9",compare:null,sortFn:(e,n)=>e.libelle.localeCompare(n.libelle)}]}}return e.\u0275fac=function(n){return new(n||e)(s.Nb(a.a),s.Nb(l.f),s.Nb(p),s.Nb(d.a))},e.\u0275cmp=s.Hb({type:e,selectors:[["app-service"]],decls:24,vars:11,consts:[["bdColor","rgba(0, 0, 0, 0.8)","size","large","color","#fff","type","line-scale-pulse-out",3,"fullScreen"],[2,"color","white"],[3,"nzSelectedIndex","nzSize","nzSelectedIndexChange"],[3,"nzTitle"],["nzTableLayout","fixed",3,"nzData","nzPageSize","nzPageIndex"],["sortTable",""],["nzCustomFilter","",3,"nzSortFn",4,"ngFor","ngForOf"],[4,"ngFor","ngForOf"],["menu","nzDropdownMenu"],[1,"ant-table-filter-dropdown"],[1,"search-box"],["type","text","nz-input","","placeholder","chercher un service",3,"ngModel","ngModelChange"],["nz-button","","nzSize","small","nzType","primary",1,"search-button",3,"click"],["nz-button","","nzSize","small",3,"click"],[3,"nzTitle",4,"ngIf"],["nzCustomFilter","",3,"nzSortFn"],[3,"nzVisible","nzActive","nzDropdownMenu","nzVisibleChange"],["nz-icon","","nzType","search"],["nz-button","","nzType","primary","nzShape","circle",3,"click"],["nz-icon","","nzType","edit","nzTheme","outline"],["nz-popconfirm","","nzPopconfirmTitle","Voulez-vous vraiment supprimer?","nzPopconfirmPlacement","topRight","nz-button","","nzType","primary","nzShape","circle",3,"nzOnConfirm","nzOnCancel"],["nz-icon","","nzType","delete","nzTheme","outline"],["nz-form","",3,"formGroup"],["nz-row",""],["nz-col","","nzSpan","24"],[3,"nzSpan"],["nzErrorTip","Merci de renseigner ce champs!",3,"nzSpan"],["formControlName","libelle","nz-input","","placeholder","saisir le nom du service",3,"keyup"],["nz-button","","nzType","primary",3,"disabled","click"],["nz-icon","","nzType","check"],["nz-button","","nzType","default",3,"click"],["nz-icon","","nzType","close"]],template:function(e,n){if(1&e&&(s.Tb(0,"ngx-spinner",0),s.Tb(1,"p",1),s.Dc(2," Chargement... "),s.Sb(),s.Sb(),s.Tb(3,"nz-tabset",2),s.ac("nzSelectedIndexChange",function(e){return n.indexOfTab=e}),s.Tb(4,"nz-tab",3),s.Tb(5,"nz-table",4,5),s.Tb(7,"thead"),s.Tb(8,"tr"),s.Bc(9,L,4,5,"th",6),s.Tb(10,"th"),s.Dc(11," Actions "),s.Sb(),s.Sb(),s.Sb(),s.Tb(12,"tbody"),s.Bc(13,A,11,1,"tr",7),s.Sb(),s.Sb(),s.Sb(),s.Tb(14,"nz-dropdown-menu",null,8),s.Tb(16,"div",9),s.Tb(17,"div",10),s.Tb(18,"input",11),s.ac("ngModelChange",function(e){return n.searchValue=e}),s.Sb(),s.Tb(19,"button",12),s.ac("click",function(){return n.search()}),s.Dc(20," Chercher "),s.Sb(),s.Tb(21,"button",13),s.ac("click",function(){return n.reset()}),s.Dc(22,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Bc(23,x,19,5,"nz-tab",14),s.Sb()),2&e){const e=s.pc(6);s.jc("fullScreen",!0),s.zb(3),s.jc("nzSelectedIndex",n.indexOfTab)("nzSize","large"),s.zb(1),s.jc("nzTitle","Liste des services "),s.zb(1),s.jc("nzData",n.listOfDisplayData)("nzPageSize",3)("nzPageIndex",n.pageIndex),s.zb(4),s.jc("ngForOf",n.listOfColumn),s.zb(4),s.jc("ngForOf",e.data),s.zb(5),s.jc("ngModel",n.searchValue),s.zb(5),s.jc("ngIf",n.is_admin)}},directives:[z.a,m.b,m.a,f.c,f.h,f.i,t.m,f.b,f.g,f.e,S.c,g.b,l.d,l.m,l.o,v.a,T.a,D.a,t.n,f.f,f.a,O.a,E.a,E.b,k.a,l.s,l.n,P.b,l.i,C.c,C.a,P.c,P.d,P.a,l.h],styles:[""]}),e})();var N=i("ekK5");class V{}var y=i("Tm1e");let I=(()=>{class e{constructor(e,n,i){this.http=e,this.Cookie=n,this.router=i,this.url=c.a.backend+"/profil"}getList(){return this.http.get(this.url+"/list",this.http_get_request())}createProfil(e){return this.http.post(this.url+"/creer-profil",e,this.http_get_request())}updateProfil(e){return this.http.put(this.url+"/modifier-profil",e,this.http_get_request())}deleteProfil(e){return this.http.delete(`${this.url}/supprimer-profil/${e}`,this.http_get_request())}http_get_request(){return this.checkCredentials(),{headers:new b.d({"Content-Type":"application/json",Accept:"application/json","Access-Control-Allow-Origin":"*",Authorization:"Bearer "+this.getAccessToken().accessToken})}}checkCredentials(){this.Cookie.check("access_token")||this.logout()}logout(){this.Cookie.delete("access_token","/"),this.Cookie.delete("user","/"),this.router.navigate(["/login"])}getAccessToken(){let e=new u.a;return e=JSON.parse(this.Cookie.get("access_token")),e}}return e.\u0275fac=function(n){return new(n||e)(s.Xb(b.b),s.Xb(h.a),s.Xb(r.d))},e.\u0275prov=s.Jb({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();var M=i("zAKX");function j(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-filter-trigger",51),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc(2).visibleNom=n}),s.Ob(1,"i",52),s.Sb()}if(2&e){const e=s.dc(2),n=s.pc(14);s.jc("nzVisible",e.visibleNom)("nzActive",e.searchValueNom.length>0)("nzDropdownMenu",n)}}function w(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-filter-trigger",51),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc(2).visiblePrenom=n}),s.Ob(1,"i",52),s.Sb()}if(2&e){const e=s.dc(2),n=s.pc(23);s.jc("nzVisible",e.visiblePrenom)("nzActive",e.searchValuePrenom.length>0)("nzDropdownMenu",n)}}function R(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-filter-trigger",51),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc(2).visibleService=n}),s.Ob(1,"i",52),s.Sb()}if(2&e){const e=s.dc(2),n=s.pc(59);s.jc("nzVisible",e.visibleService)("nzActive",e.searchValueService.length>0)("nzDropdownMenu",n)}}function _(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-filter-trigger",51),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc(2).visibleEmail=n}),s.Ob(1,"i",52),s.Sb()}if(2&e){const e=s.dc(2),n=s.pc(32);s.jc("nzVisible",e.visibleEmail)("nzActive",e.searchValueEmail.length>0)("nzDropdownMenu",n)}}function U(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-filter-trigger",51),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc(2).visibleSexe=n}),s.Ob(1,"i",52),s.Sb()}if(2&e){const e=s.dc(2),n=s.pc(41);s.jc("nzVisible",e.visibleSexe)("nzActive",e.searchValueSexe.length>0)("nzDropdownMenu",n)}}function B(e,n){if(1&e){const e=s.Ub();s.Tb(0,"nz-filter-trigger",51),s.ac("nzVisibleChange",function(n){return s.rc(e),s.dc(2).visibleProfil=n}),s.Ob(1,"i",52),s.Sb()}if(2&e){const e=s.dc(2),n=s.pc(50);s.jc("nzVisible",e.visibleProfil)("nzActive",e.searchValueProfil.length>0)("nzDropdownMenu",n)}}function q(e,n){if(1&e&&(s.Tb(0,"th",49),s.Dc(1),s.Bc(2,j,2,3,"nz-filter-trigger",50),s.Bc(3,w,2,3,"nz-filter-trigger",50),s.Bc(4,R,2,3,"nz-filter-trigger",50),s.Bc(5,_,2,3,"nz-filter-trigger",50),s.Bc(6,U,2,3,"nz-filter-trigger",50),s.Bc(7,B,2,3,"nz-filter-trigger",50),s.Sb()),2&e){const e=n.$implicit;s.jc("nzSortFn",e.sortFn),s.zb(1),s.Fc(" ",e.title," "),s.zb(1),s.jc("ngIf","Nom"==e.title),s.zb(1),s.jc("ngIf","Pr\xe9nom"==e.title),s.zb(1),s.jc("ngIf","Service"==e.title),s.zb(1),s.jc("ngIf","Email"==e.title),s.zb(1),s.jc("ngIf","Sexe"==e.title),s.zb(1),s.jc("ngIf","Profil"==e.title)}}function $(e,n){1&e&&(s.Tb(0,"th"),s.Dc(1," Actions "),s.Sb())}function G(e,n){if(1&e&&(s.Tb(0,"span"),s.Dc(1),s.Sb()),2&e){const e=n.$implicit;s.zb(1),s.Ec(e.libelle+" ")}}function H(e,n){if(1&e){const e=s.Ub();s.Tb(0,"td"),s.Tb(1,"nz-space"),s.Tb(2,"nz-space-item"),s.Tb(3,"button",53),s.ac("click",function(){s.rc(e);const n=s.dc().$implicit;return s.dc().updateForm(n)}),s.Ob(4,"i",54),s.Sb(),s.Sb(),s.Tb(5,"nz-space-item"),s.Tb(6,"button",55),s.ac("nzOnConfirm",function(){s.rc(e);const n=s.dc().$implicit;return s.dc().confirmMsgDelete(n)})("nzOnCancel",function(){return s.rc(e),s.dc(2).cancelMsgDelete()}),s.Ob(7,"i",56),s.Sb(),s.Sb(),s.Sb(),s.Sb()}}function K(e,n){if(1&e&&(s.Tb(0,"tr"),s.Tb(1,"td"),s.Dc(2),s.Sb(),s.Tb(3,"td"),s.Dc(4),s.Sb(),s.Tb(5,"td"),s.Dc(6),s.Sb(),s.Tb(7,"td"),s.Dc(8),s.Sb(),s.Tb(9,"td"),s.Bc(10,G,2,1,"span",8),s.Sb(),s.Tb(11,"td"),s.Dc(12),s.Sb(),s.Bc(13,H,8,0,"td",7),s.Sb()),2&e){const e=n.$implicit,i=s.dc();s.zb(2),s.Ec(null==e?null:e.nom),s.zb(2),s.Ec(null==e?null:e.prenom),s.zb(2),s.Ec(null==e?null:e.email),s.zb(2),s.Ec(null==e?null:e.sexe),s.zb(2),s.jc("ngForOf",null==e?null:e.roles),s.zb(2),s.Ec(null==e||null==e.serviceB?null:e.serviceB.libelle),s.zb(1),s.jc("ngIf",i.is_admin)}}function X(e,n){if(1&e&&s.Ob(0,"nz-option",36),2&e){const e=n.$implicit;s.jc("nzLabel",e.libelle)("nzValue",e)}}function J(e,n){if(1&e&&s.Ob(0,"nz-option",36),2&e){const e=n.$implicit;s.jc("nzLabel",e.libelle)("nzValue",e)}}const W=[{path:"",component:o,children:[{path:"service",component:F},{path:"user",component:(()=>{class e{constructor(e,n,i,t,r,s){this.behaviorService=e,this.fb=n,this.personneService=i,this.profilService=t,this.serviceBService=r,this.tokenService=s,this.environment=c.a,this.perso=new N.a,this.listOfColumn=[],this.searchValueNom="",this.searchValuePrenom="",this.searchValueEmail="",this.searchValueSexe="",this.searchValueProfil="",this.searchValueService="",this.visibleNom=!1,this.visiblePrenom=!1,this.visibleEmail=!1,this.visibleSexe=!1,this.visibleProfil=!1,this.visibleService=!1,this.token=this.tokenService.getAccessToken()}ngOnInit(){this.behaviorService.setBreadcrumbItems(["Accueil","Gestion Utilisateur","User"]),this.makePersonneForm(null),this.list(),this.listOfColumnHeadeer(),this.listProfil(),this.listService(),this.adminIsConnect=this.isAdmin(),this.is_admin=this.isAdmin()}isAdmin(){return this.token.roles.indexOf(c.a.ROLE_ADMIN)>-1}listProfil(){this.profilService.getList().subscribe(e=>{if(this.profilList=e,console.log("Profil List ==>",this.profilList),0==this.adminIsConnect)for(let n of this.profilList)"ROLE_PERSONNE"==n.name&&(this.rolePersonne=new V,this.rolePersonne=n,this.rolePersonne.libelle="PERSONNE",this.perso.roles=[],this.perso.roles.push(this.rolePersonne),this.makePersonneForm(this.perso));console.log(this.rolePersonne),this.profilList.forEach(e=>{switch(e.name){case c.a.ROLE_ADMIN:e.libelle="ADMINISTRATEUR";break;case c.a.ROLE_DEMANDEUR:e.libelle="DEMANDEUR";break;case c.a.ROLE_GESTIONNAIRE:e.libelle="GESTIONNAIRE";break;case c.a.ROLE_PERSONNE:e.libelle="PERSONNE";break;case c.a.ROLE_VALIDATEUR:e.libelle="VALIDATEUR";break;case c.a.ROLE_AUDITEUR:e.libelle="AUDITEUR"}})},e=>{console.log("error getList Profil ==>",e.message," ",e.status," ",e.statusText)})}listService(){this.serviceBService.getList().subscribe(e=>{this.serviceBList=e,console.log("Service List ==>",this.serviceBList)},e=>{console.log("error getList Service ==>",e.message," ",e.status," ",e.statusText)})}makePersonneForm(e){this.validatePersonneForm=this.fb.group({id:[null!=e?e.id:null],nom:[null!=e?e.nom:null,[l.r.required]],prenom:[null!=e?e.prenom:null,[l.r.required]],sexe:[null!=e?e.sexe:null,[l.r.required]],email:[null!=e?e.email:null,[l.r.required]],roles:[null!=e?e.roles:null,[l.r.required]],serviceB:[null!=e?e.serviceB:null,[l.r.required]],username:[null!=e?e.username:null],password:[""],confirm_password:[""]},{validator:this.ConfirmedValidator("password","confirm_password")})}ConfirmedValidator(e,n){return i=>{const t=i.controls[n];t.errors&&!t.errors.confirmedValidator||t.setErrors(i.controls[e].value!==t.value?{confirmedValidator:!0}:null)}}resetPersonneForm(e){e.preventDefault(),this.validatePersonneForm.reset();for(const n in this.validatePersonneForm.controls)this.validatePersonneForm.controls[n].markAsPristine(),this.validatePersonneForm.controls[n].updateValueAndValidity();this.makePersonneForm(null),this.indexOfTab=0,this.pageIndex=1}submitPersonneForm(){for(const e in this.validatePersonneForm.controls)this.validatePersonneForm.controls[e].markAsDirty(),this.validatePersonneForm.controls[e].updateValueAndValidity();if(this.validatePersonneForm.valid){const e=this.validatePersonneForm.value;if(null==e.id)this.personneService.createPersonne(e).subscribe(e=>{e.roles.forEach(e=>{switch(e.name){case c.a.ROLE_ADMIN:e.libelle="ADMINISTRATEUR";break;case c.a.ROLE_DEMANDEUR:e.libelle="DEMANDEUR";break;case c.a.ROLE_GESTIONNAIRE:e.libelle="GESTIONNAIRE";break;case c.a.ROLE_PERSONNE:e.libelle="PERSONNE";break;case c.a.ROLE_VALIDATEUR:e.libelle="VALIDATEUR";break;case c.a.ROLE_AUDITEUR:e.libelle="AUDITEUR"}}),this.personneList.unshift(e),this.personneList=[...this.personneList],this.listOfDisplayData=[...this.personneList],this.makePersonneForm(null),console.log("Enregistrement ok"),this.indexOfTab=0,this.pageIndex=1},e=>{console.log("Enregistrement non ok")});else{const n=this.personneList.findIndex(n=>n.id==e.id);this.personneService.updatePersonne(e).subscribe(e=>{console.log(this.personneList),console.log(e),this.personneList[n]=e,this.personneList=[...this.personneList],this.listOfDisplayData=[...this.personneList],console.log(this.personneList),this.makePersonneForm(null),console.log("Update ok"),this.indexOfTab=0,this.pageIndex=1},e=>{console.log("Update non ok")})}}}list(){this.personneService.getList().subscribe(e=>{this.personneList=e,this.personneList.forEach(e=>{e.roles.forEach(e=>{switch(e.name){case c.a.ROLE_ADMIN:e.libelle="ADMINISTRATEUR";break;case c.a.ROLE_DEMANDEUR:e.libelle="DEMANDEUR";break;case c.a.ROLE_GESTIONNAIRE:e.libelle="GESTIONNAIRE";break;case c.a.ROLE_PERSONNE:e.libelle="PERSONNE";break;case c.a.ROLE_VALIDATEUR:e.libelle="VALIDATEUR";break;case c.a.ROLE_AUDITEUR:e.libelle="AUDITEUR"}})}),console.log("Personne List ==>",this.personneList),this.listOfDisplayData=[...this.personneList],this.pageIndex=1},e=>{console.log("error getList personne ==>",e.message," ",e.status," ",e.statusText)})}resetNom(){this.searchValueNom="",this.searchNom()}searchNom(){this.visibleNom=!1,this.listOfDisplayData=this.personneList.filter(e=>-1!==e.nom.indexOf(this.searchValueNom))}resetPrenom(){this.searchValuePrenom="",this.searchPrenom()}searchPrenom(){this.visiblePrenom=!1,this.listOfDisplayData=this.personneList.filter(e=>-1!==e.prenom.indexOf(this.searchValuePrenom))}resetEmail(){this.searchValueEmail="",this.searchEmail()}searchEmail(){this.visibleEmail=!1,this.listOfDisplayData=this.personneList.filter(e=>-1!==e.email.indexOf(this.searchValueEmail))}resetSexe(){this.searchValueNom="",this.searchSexe()}searchSexe(){this.visibleSexe=!1,this.listOfDisplayData=this.personneList.filter(e=>-1!==e.sexe.indexOf(this.searchValueSexe))}resetProfil(){this.searchValueProfil="",this.searchProfil()}searchProfil(){this.visibleProfil=!1,this.listOfDisplayData=this.personneList.filter(e=>-1!==e.profil.libelle.indexOf(this.searchValueProfil))}resetService(){this.searchValueService="",this.searchProfil()}searchService(){this.visibleService=!1,this.listOfDisplayData=this.personneList.filter(e=>-1!==e.serviceB.libelle.indexOf(this.searchValueService))}updateForm(e){this.makePersonneForm(e),this.indexOfTab=1}confirmMsgDelete(e){this.personneService.deletePersonne(e.id).subscribe(e=>{console.log("data du delete ==>",e),this.list()},e=>{console.log("error delete Personne ==>",e.message," ",e.status," ",e.statusText)})}cancelMsgDelete(){}listOfColumnHeadeer(){this.listOfColumn=[{title:"Nom",compare:null,sortFn:(e,n)=>e.nom.localeCompare(n.nom)},{title:"Pr\xe9nom",compare:null,sortFn:(e,n)=>e.prenom.localeCompare(n.prenom)},{title:"Email",compare:null,sortFn:(e,n)=>e.email.localeCompare(n.email)},{title:"Sexe",compare:null,sortFn:(e,n)=>e.sexe.localeCompare(n.sexe)},{title:"Profil",compare:null,sortFn:(e,n)=>e.profil.libelle.localeCompare(n.profil.libelle)},{title:"Service",compare:null,sortFn:(e,n)=>e.serviceB.libelle.localeCompare(n.serviceB.libelle)}]}}return e.\u0275fac=function(n){return new(n||e)(s.Nb(a.a),s.Nb(l.f),s.Nb(y.a),s.Nb(I),s.Nb(p),s.Nb(d.a))},e.\u0275cmp=s.Hb({type:e,selectors:[["app-user-run"]],decls:136,vars:45,consts:[["bdColor","rgba(0, 0, 0, 0.8)","size","large","color","#fff","type","line-scale-pulse-out",3,"fullScreen"],[2,"color","white"],[3,"nzSelectedIndex","nzSize","nzSelectedIndexChange"],[3,"nzTitle"],["nzTableLayout","fixed",3,"nzData","nzPageSize","nzPageIndex"],["sortTable",""],["nzCustomFilter","",3,"nzSortFn",4,"ngFor","ngForOf"],[4,"ngIf"],[4,"ngFor","ngForOf"],["menuNom","nzDropdownMenu"],[1,"ant-table-filter-dropdown"],[1,"search-box"],["type","text","nz-input","","placeholder","chercher un nom",3,"ngModel","ngModelChange"],["nz-button","","nzSize","small","nzType","primary",1,"search-button",3,"click"],["nz-button","","nzSize","small",3,"click"],["menuPrenom","nzDropdownMenu"],["type","text","nz-input","","placeholder","chercher un prenom",3,"ngModel","ngModelChange"],["menuEmail","nzDropdownMenu"],["type","text","nz-input","","placeholder","chercher un email",3,"ngModel","ngModelChange"],["menuSexe","nzDropdownMenu"],["type","text","nz-input","","placeholder","chercher un sexe",3,"ngModel","ngModelChange"],["menuProfil","nzDropdownMenu"],["type","text","nz-input","","placeholder","chercher un profil",3,"ngModel","ngModelChange"],["menuService","nzDropdownMenu"],["type","text","nz-input","","placeholder","chercher un service",3,"ngModel","ngModelChange"],["nz-form","",3,"formGroup"],["nz-row",""],["nz-col","","nzSpan","12",2,"padding-right","1.5em"],[3,"nzSpan"],["nzErrorTip","Merci de renseigner ce champs!",3,"nzSpan"],["formControlName","nom","nz-input","","placeholder","saisir le nom"],["formControlName","prenom","nz-input","","placeholder","saisir le pr\xe9nom"],["nzShowSearch","","nzAllowClear","","nzPlaceHolder","Selectionner un service","formControlName","serviceB"],[3,"nzLabel","nzValue",4,"ngFor","ngForOf"],["formControlName","roles","nzMode","multiple","nzPlaceHolder","S\xe9lectionner un groupe de profil",3,"nzSize","nzDisabled"],["nzShowSearch","","nzAllowClear","","nzPlaceHolder","Selectionner un sexe","formControlName","sexe"],[3,"nzLabel","nzValue"],["nzErrorTip","Merci de renseigner un email valide!",3,"nzSpan"],["formControlName","email","nz-input","","placeholder","saisir le mail",3,"email"],["nz-col","","nzSpan","8",2,"padding-right","1.5em"],["formControlName","username","nz-input","","placeholder","saisir le login"],["nz-input","","formControlName","password","placeholder","saisir le mot de passe","type","password"],["nzErrorTip","Le mot de passe et la confirmation ne correspondent pas",3,"nzSpan"],["nz-input","","formControlName","confirm_password","placeholder","re-saisir le mot de passe","type","password"],["nz-col","","nzSpan","24"],["nz-button","","nzType","default",3,"click"],["nz-icon","","nzType","close"],["nz-button","","nzType","primary",3,"disabled","click"],["nz-icon","","nzType","check"],["nzCustomFilter","",3,"nzSortFn"],[3,"nzVisible","nzActive","nzDropdownMenu","nzVisibleChange",4,"ngIf"],[3,"nzVisible","nzActive","nzDropdownMenu","nzVisibleChange"],["nz-icon","","nzType","search"],["nz-button","","nzType","primary","nzShape","circle",3,"click"],["nz-icon","","nzType","edit","nzTheme","outline"],["nz-popconfirm","","nzPopconfirmTitle","Voulez-vous vraiment supprimer?","nzPopconfirmPlacement","topRight","nz-button","","nzType","primary","nzShape","circle",3,"nzOnConfirm","nzOnCancel"],["nz-icon","","nzType","delete","nzTheme","outline"]],template:function(e,n){if(1&e&&(s.Tb(0,"ngx-spinner",0),s.Tb(1,"p",1),s.Dc(2," Chargement... "),s.Sb(),s.Sb(),s.Tb(3,"nz-tabset",2),s.ac("nzSelectedIndexChange",function(e){return n.indexOfTab=e}),s.Tb(4,"nz-tab",3),s.Tb(5,"nz-table",4,5),s.Tb(7,"thead"),s.Tb(8,"tr"),s.Bc(9,q,8,8,"th",6),s.Bc(10,$,2,0,"th",7),s.Sb(),s.Sb(),s.Tb(11,"tbody"),s.Bc(12,K,14,7,"tr",8),s.Sb(),s.Sb(),s.Sb(),s.Tb(13,"nz-dropdown-menu",null,9),s.Tb(15,"div",10),s.Tb(16,"div",11),s.Tb(17,"input",12),s.ac("ngModelChange",function(e){return n.searchValueNom=e}),s.Sb(),s.Tb(18,"button",13),s.ac("click",function(){return n.searchNom()}),s.Dc(19," Chercher "),s.Sb(),s.Tb(20,"button",14),s.ac("click",function(){return n.resetNom()}),s.Dc(21,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(22,"nz-dropdown-menu",null,15),s.Tb(24,"div",10),s.Tb(25,"div",11),s.Tb(26,"input",16),s.ac("ngModelChange",function(e){return n.searchValuePrenom=e}),s.Sb(),s.Tb(27,"button",13),s.ac("click",function(){return n.searchPrenom()}),s.Dc(28," Chercher "),s.Sb(),s.Tb(29,"button",14),s.ac("click",function(){return n.resetPrenom()}),s.Dc(30,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(31,"nz-dropdown-menu",null,17),s.Tb(33,"div",10),s.Tb(34,"div",11),s.Tb(35,"input",18),s.ac("ngModelChange",function(e){return n.searchValueEmail=e}),s.Sb(),s.Tb(36,"button",13),s.ac("click",function(){return n.searchEmail()}),s.Dc(37," Chercher "),s.Sb(),s.Tb(38,"button",14),s.ac("click",function(){return n.resetEmail()}),s.Dc(39,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(40,"nz-dropdown-menu",null,19),s.Tb(42,"div",10),s.Tb(43,"div",11),s.Tb(44,"input",20),s.ac("ngModelChange",function(e){return n.searchValueSexe=e}),s.Sb(),s.Tb(45,"button",13),s.ac("click",function(){return n.searchSexe()}),s.Dc(46," Chercher "),s.Sb(),s.Tb(47,"button",14),s.ac("click",function(){return n.resetSexe()}),s.Dc(48,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(49,"nz-dropdown-menu",null,21),s.Tb(51,"div",10),s.Tb(52,"div",11),s.Tb(53,"input",22),s.ac("ngModelChange",function(e){return n.searchValueProfil=e}),s.Sb(),s.Tb(54,"button",13),s.ac("click",function(){return n.searchProfil()}),s.Dc(55," Chercher "),s.Sb(),s.Tb(56,"button",14),s.ac("click",function(){return n.resetProfil()}),s.Dc(57,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(58,"nz-dropdown-menu",null,23),s.Tb(60,"div",10),s.Tb(61,"div",11),s.Tb(62,"input",24),s.ac("ngModelChange",function(e){return n.searchValueService=e}),s.Sb(),s.Tb(63,"button",13),s.ac("click",function(){return n.searchService()}),s.Dc(64," Chercher "),s.Sb(),s.Tb(65,"button",14),s.ac("click",function(){return n.resetService()}),s.Dc(66,"Annuler"),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Tb(67,"nz-tab",3),s.Tb(68,"form",25),s.Tb(69,"div",26),s.Tb(70,"div",27),s.Tb(71,"nz-form-item"),s.Tb(72,"nz-form-label",28),s.Dc(73,"Nom"),s.Sb(),s.Tb(74,"nz-form-control",29),s.Ob(75,"input",30),s.Sb(),s.Sb(),s.Sb(),s.Tb(76,"div",27),s.Tb(77,"nz-form-item"),s.Tb(78,"nz-form-label",28),s.Dc(79,"Pr\xe9nom"),s.Sb(),s.Tb(80,"nz-form-control",29),s.Ob(81,"input",31),s.Sb(),s.Sb(),s.Sb(),s.Tb(82,"div",27),s.Tb(83,"nz-form-item"),s.Tb(84,"nz-form-label",28),s.Dc(85,"Service"),s.Sb(),s.Tb(86,"nz-select",32),s.Bc(87,X,1,2,"nz-option",33),s.Sb(),s.Sb(),s.Sb(),s.Tb(88,"div",27),s.Tb(89,"nz-form-item"),s.Tb(90,"nz-form-label",28),s.Dc(91,"Profil"),s.Sb(),s.Tb(92,"nz-select",34),s.Bc(93,J,1,2,"nz-option",33),s.Sb(),s.Sb(),s.Sb(),s.Tb(94,"div",27),s.Tb(95,"nz-form-item"),s.Tb(96,"nz-form-label",28),s.Dc(97,"Sexe"),s.Sb(),s.Tb(98,"nz-select",35),s.Ob(99,"nz-option",36),s.Ob(100,"nz-option",36),s.Ob(101,"nz-option",36),s.Sb(),s.Sb(),s.Sb(),s.Tb(102,"div",27),s.Tb(103,"nz-form-item"),s.Tb(104,"nz-form-label",28),s.Dc(105,"E-mail"),s.Sb(),s.Tb(106,"nz-form-control",37),s.Ob(107,"input",38),s.Sb(),s.Sb(),s.Sb(),s.Tb(108,"div",39),s.Tb(109,"nz-form-item"),s.Tb(110,"nz-form-label",28),s.Dc(111,"Login"),s.Sb(),s.Tb(112,"nz-form-control",29),s.Ob(113,"input",40),s.Sb(),s.Sb(),s.Sb(),s.Tb(114,"div",39),s.Tb(115,"nz-form-item"),s.Tb(116,"nz-form-label",28),s.Dc(117,"Mot de passe "),s.Sb(),s.Tb(118,"nz-form-control",29),s.Ob(119,"input",41),s.Sb(),s.Sb(),s.Sb(),s.Tb(120,"div",39),s.Tb(121,"nz-form-item"),s.Tb(122,"nz-form-label",28),s.Dc(123,"Confirmer le Mot de passe "),s.Sb(),s.Tb(124,"nz-form-control",42),s.Ob(125,"input",43),s.Sb(),s.Sb(),s.Sb(),s.Tb(126,"div",44),s.Tb(127,"nz-space"),s.Tb(128,"nz-space-item"),s.Tb(129,"button",45),s.ac("click",function(e){return n.resetPersonneForm(e)}),s.Ob(130,"i",46),s.Dc(131," Annuler "),s.Sb(),s.Sb(),s.Tb(132,"nz-space-item"),s.Tb(133,"button",47),s.ac("click",function(){return n.submitPersonneForm()}),s.Ob(134,"i",48),s.Dc(135," Enregistrer "),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb(),s.Sb()),2&e){const e=s.pc(6);s.jc("fullScreen",!0),s.zb(3),s.jc("nzSelectedIndex",n.indexOfTab)("nzSize","large"),s.zb(1),s.jc("nzTitle","Liste des personnes"),s.zb(1),s.jc("nzData",n.listOfDisplayData)("nzPageSize",5)("nzPageIndex",n.pageIndex),s.zb(4),s.jc("ngForOf",n.listOfColumn),s.zb(1),s.jc("ngIf",n.is_admin),s.zb(2),s.jc("ngForOf",e.data),s.zb(5),s.jc("ngModel",n.searchValueNom),s.zb(9),s.jc("ngModel",n.searchValuePrenom),s.zb(9),s.jc("ngModel",n.searchValueEmail),s.zb(9),s.jc("ngModel",n.searchValueSexe),s.zb(9),s.jc("ngModel",n.searchValueProfil),s.zb(9),s.jc("ngModel",n.searchValueService),s.zb(5),s.jc("nzTitle","Nouvelle personne"),s.zb(1),s.jc("formGroup",n.validatePersonneForm),s.zb(4),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(4),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(4),s.jc("nzSpan",24),s.zb(3),s.jc("ngForOf",n.serviceBList),s.zb(3),s.jc("nzSpan",24),s.zb(2),s.jc("nzSize","default")("nzDisabled",!n.adminIsConnect),s.zb(1),s.jc("ngForOf",n.profilList),s.zb(3),s.jc("nzSpan",24),s.zb(3),s.jc("nzLabel","Masculin")("nzValue","Masculin"),s.zb(1),s.jc("nzLabel","F\xe9minin")("nzValue","F\xe9minin"),s.zb(1),s.jc("nzLabel","Non pr\xe9cis\xe9")("nzValue","Non pr\xe9cis\xe9"),s.zb(3),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(1),s.jc("email",!0),s.zb(3),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(4),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(4),s.jc("nzSpan",24),s.zb(2),s.jc("nzSpan",24),s.zb(9),s.jc("disabled",n.validatePersonneForm.invalid)}},directives:[z.a,m.b,m.a,f.c,f.h,f.i,t.m,t.n,f.e,S.c,g.b,l.d,l.m,l.o,v.a,T.a,D.a,l.s,l.n,P.b,l.i,C.c,C.a,P.c,P.d,P.a,l.h,M.b,M.a,l.e,E.a,E.b,O.a,f.b,f.g,f.f,f.a,k.a],styles:[""]}),e})()}]}];let Q=(()=>{class e{}return e.\u0275mod=s.Lb({type:e}),e.\u0275inj=s.Kb({factory:function(n){return new(n||e)},imports:[[r.g.forChild(W)],r.g]}),e})();var Z=i("yW9e"),Y=i("yNE/"),ee=i("Q8cG"),ne=i("bE2y"),ie=i("SKKP"),te=i("0jEk"),re=i("3/1E"),se=i("dEAy"),oe=i("5vDB");let le=(()=>{class e{}return e.\u0275mod=s.Lb({type:e}),e.\u0275inj=s.Kb({factory:function(n){return new(n||e)},imports:[[z.b,t.c,Q,Z.e,O.b,Y.c,ee.e,r.g,te.a,v.c,l.p,E.c,P.e,ne.b,C.b,g.c,m.c,f.d,M.c,ie.b,k.b,l.j,re.b,S.b,se.a,oe.b]]}),e})()}}]);