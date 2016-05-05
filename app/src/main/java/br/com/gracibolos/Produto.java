package br.com.gracibolos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rogerio on 18/04/2016.
 */
public class Produto implements Serializable{

        private static final long serialVersionUID = 1L;
        private int id;
        private String foto;
        private Integer status;
        private Calendar fabricacao;
        private Calendar vencimento;
        private String codigo;
        private String nome;
        private Long tipo;
        private BigDecimal peso;
        private Long unidade;
        private int estoque;
        private double custo;
        private double valor;
        private String obs;
        private  String receita;

        public Produto() {
        }

        //Tudo que esta sendo exibido no ListView se encontra no método toString().
        @Override
        public String toString() {
            return nome;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Calendar getFabricacao() {
            return fabricacao;
        }

        public void setFabricacao(Calendar fabricacao) {
            this.fabricacao = fabricacao;
        }

        public Calendar getVencimento() {
            return vencimento;
        }

        public void setVencimento(Calendar vencimento) {
            this.vencimento = vencimento;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getTipo() {
            return tipo;
        }

        public void setTipo(Long tipo) {
            this.tipo = tipo;
        }

        public BigDecimal getPeso() {
            return peso;
        }

        public void setPeso(BigDecimal peso) {
            this.peso = peso;
        }

        public Long getUnidade() {
            return unidade;
        }

        public void setUnidade(Long unidade) {
            this.unidade = unidade;
        }

        public int getEstoque() {
            return estoque;
        }

        public void setEstoque(int estoque) {
            this.estoque = estoque;
        }

        public double getCusto() {
            return custo;
        }

        public void setCusto(double custo) {
            this.custo = custo;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public String getObs() {
            return obs;
        }

        public void setObs(String obs) {
            this.obs = obs;
        }

        public String getReceita() {
            return receita;
        }

        public void setReceita(String receita) {
            this.receita = receita;
        }
}
