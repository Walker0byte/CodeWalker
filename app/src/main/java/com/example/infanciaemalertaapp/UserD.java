package com.example.infanciaemalertaapp;

import android.graphics.Bitmap;

import com.google.firebase.storage.StorageReference;

public class UserD {

  
    public String descricao;
    public String NMenor, IdadeM, SexoM, MaeM, PaiM, EscolaM, EnderecoM, CidadeM, EstadoM;
    public String email;
   

    public UserD(){

        
    }
    public UserD(String NMenor, String IdadeM, String SexoM, String MaeM, String PaiM,
                 String EscolaM, String EnderecoM, String CidadeM, String EstadoM, String descricao) {
        this.NMenor = NMenor;
        this.IdadeM = IdadeM;
        this.SexoM = SexoM;
        this.MaeM = MaeM;
        this.PaiM = PaiM;
        this.EscolaM = EscolaM;
        this.EnderecoM = EnderecoM;
        this.CidadeM = CidadeM;
        this.EstadoM = EstadoM;
        this.descricao = descricao;


    }


    @Override
        public String toString () {
        return  this.NMenor + "\n" + this.IdadeM +  "\n" + this.SexoM + "\n" + this.MaeM + "\n" + this.PaiM
                + "\n" + this.EscolaM + "\n" + this.EnderecoM + "\n" + this.CidadeM + "\n" + this.EstadoM + "\n" + this.descricao;}

        }




