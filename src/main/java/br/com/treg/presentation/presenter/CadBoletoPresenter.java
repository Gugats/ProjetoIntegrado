/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.BoletoBO;
import br.com.treg.business.bo.NotaFiscalBO;
import br.com.treg.business.model.Boleto;
import br.com.treg.presentation.view.CadBoletoView;
import br.com.treg.presentation.view.impl.CadBoletoViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadBoletoPresenter implements CadBoletoView.CadBoletoViewListener{

    private CadBoletoView view;
    
    private BoletoBO bo;
    
    private NotaFiscalBO notaFiscalBO;

    public CadBoletoPresenter() {
        view = new CadBoletoViewImpl();
        this.view.addListener(this);
        
        bo = new BoletoBO();
        notaFiscalBO = new NotaFiscalBO();
        
        this.view.populaListaBoletos(bo.listAll());
        this.view.populaComboNF(notaFiscalBO.listAll());
    }
    
    @Override
    public CadBoletoView getView() {
        return this.view;
    }

    @Override
    public void salvar(Boleto boleto) {
        try{
            bo.saveOrUpdate(boleto);
            this.view.sucesso("Boleto salvo com sucesso!");
            this.view.populaListaBoletos(bo.listAll());
        }catch(RuntimeException re){
            this.view.falha("Ocorreu um erro ao realizar operação! Contate o administrador!");
        }
    }

    @Override
    public void excluir(Boleto boleto) {
        try{
            bo.delete(boleto);
            this.view.sucesso("Boleto excluido com sucesso!");
            this.view.populaListaBoletos(bo.listAll());
        }catch(RuntimeException re){
            this.view.falha("Ocorreu um erro ao realizar exclusão! Contate o administrador!");
        }
    }
    
}
