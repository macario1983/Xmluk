
package com.nfehost.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

import org.hibernate.annotations.Type;

import com.nfehost.dao.hibernate.type.FinalidadeEmissaoType;
import com.nfehost.dao.hibernate.type.IdentificadorProcessoEmissaoType;
import com.nfehost.dao.hibernate.type.TipoEmissaoType;
import com.nfehost.dao.hibernate.type.TipoOperacaoType;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends Persistent {

	private static final long serialVersionUID = -7632933705216815478L;

	public enum TipoOperacao {

		ENTRADA(0, "Entrada"), 
		SAIDA(1, "Saída");

		@Getter
		private final int index;
		@Getter 
		private final String descricao;

		private TipoOperacao(int index, String descricao) {
			this.index = index;
			this.descricao = descricao;
		}

		public static TipoOperacao valueOf(int index) {
			for(TipoOperacao tipoOperacao : TipoOperacao.values()) {
				if (tipoOperacao.index == index) {
					return tipoOperacao;
				}
			}
			return null;
		}
	}

	public enum TipoEmissao {

		NORMAL(1, "Normal - emissão normal"), 
		FS(2, "Contingência FS - emissão em contingência com impressão do DANFE em Formulário de Segurança"), 
		SCAN(3, "Contingência SCAN - emissão em contingência no Sistema de Contingência do Ambiente Nacional - SCAN"), 
		DPEC(4,"Contingência DPEC - emissão em contingência com envio da Declaração Prévia de Emissão em Contingência - DPEC"), 
		FS_DA(5, "Contingência FS-DA - emissão em contingência com impressão do DANFE em Formulário de Segurança para Impressão de Documento Auxiliar de Documento Fiscal");

		@Getter
		private final int index;
		@Getter 
		private final String descricao;

		private TipoEmissao(int index, String descricao) {
			this.index = index;
			this.descricao = descricao;
		}

		public static TipoEmissao valueOf(int index) {
			for(TipoEmissao tipoEmissao : TipoEmissao.values()) {
				if (tipoEmissao.index == index) {
					return tipoEmissao;
				}
			}
			return null;
		}
	}

	public enum FinalidadeEmissao {

		NORMAL(1, "NF-e normal"), 
		AJUSTE(2, "NF-e complementar"), 
		COMPLEMENTAR(3, "NF-e de ajuste");

		@Getter 
		private int index;
		@Getter 
		private final String descricao;

		private FinalidadeEmissao(int index, String descricao) {
			this.index = index;
			this.descricao = descricao;
		}

		public static FinalidadeEmissao valueOf(int index) {
			for(FinalidadeEmissao finalidadeEmissao : FinalidadeEmissao.values()) {
				if (finalidadeEmissao.index == index) {
					return finalidadeEmissao;
				}
			}
			return null;
		}
	}

	public enum IdentificadorProcessoEmissao {

		APLICATIVO_CONTRIBUINTE(0, "Emissão de NF-e com aplicativo do contribuinte"), 
		AVULSA_FISCO(1, "Emissão de NF-e avulsa pelo Fisco"), 
		AVULSA_CONTRIBUINTE(2, "Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco"), 
		CONTRIBUINTE_FISCO(3, "Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco");

		@Getter 
		private final int index;
		@Getter 
		private final String descricao;

		private IdentificadorProcessoEmissao(int index, String descricao) {
			this.index = index;
			this.descricao = descricao;
		}

		public static IdentificadorProcessoEmissao valueOf(int index) {
			for(IdentificadorProcessoEmissao identificadorProcessoEmissao : IdentificadorProcessoEmissao.values()) {
				if (identificadorProcessoEmissao.index == index) {
					return identificadorProcessoEmissao;
				}
			}
			return null;
		}
	}

	@Size(max = 60)
	@Column(name = "descricao_natureza_operacao")
	private String descricaoNaturezaOperacao;

	@NotNull
	@Column(name = "serie")
	private Integer serie;

	@NotNull
	@Column(name = "numero")
	private Integer numero;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao")
	private Date dataEmissao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_hora_entrada_ou_saida_produto")
	private Date dataHoraEntradaOuSaidaProduto;

	@Type(type = TipoOperacaoType.TYPE)
	@Column(name = "tipo_operacao", columnDefinition = "int", length = 1)
	private TipoOperacao tipoOperacao;

	@Type(type = TipoEmissaoType.TYPE)
	@Column(name = "tipo_emissao", columnDefinition = "int", length = 1)
	private TipoEmissao tipoEmissao;

	@Type(type = FinalidadeEmissaoType.TYPE)
	@Column(name = "finalidade_emissao", columnDefinition = "int", length = 1)
	private FinalidadeEmissao finalidadeEmissao;

	@Type(type = IdentificadorProcessoEmissaoType.TYPE)
	@Column(name = "identificador_processo_emissao", columnDefinition = "int", length = 1)
	private IdentificadorProcessoEmissao identificadorProcessoEmissao;

	@NotNull
	@Column(name = "valor_icms")
	private BigDecimal valorIcms;

	@NotNull
	@Column(name = "valor_nota_fiscal")
	private BigDecimal valorNotaFiscal;

	@NotNull
	@Column(name = "chave_nfe", length = 44)
	private String chaveNfe;

	@Size(max = 28)
	@Column(name = "digest_value")
	private String digestValue;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emitente_id", referencedColumnName = "id")
	private Emitente emitente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "destinatario_id", referencedColumnName = "id")
	private Destinatario destinatario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "arquivo_nota_fiscal_id", referencedColumnName = "id")
	private Arquivo arquivoNotaFiscal;

	public String getDescricaoNaturezaOperacao() {
		return descricaoNaturezaOperacao;
	}

	public void setDescricaoNaturezaOperacao(String descricaoNaturezaOperacao) {
		this.descricaoNaturezaOperacao = descricaoNaturezaOperacao;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataHoraEntradaOuSaidaProduto() {
		return dataHoraEntradaOuSaidaProduto;
	}

	public void setDataHoraEntradaOuSaidaProduto(Date dataHoraEntradaOuSaidaProduto) {
		this.dataHoraEntradaOuSaidaProduto = dataHoraEntradaOuSaidaProduto;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public TipoEmissao getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(TipoEmissao tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}

	public FinalidadeEmissao getFinalidadeEmissao() {
		return finalidadeEmissao;
	}

	public void setFinalidadeEmissao(FinalidadeEmissao finalidadeEmissao) {
		this.finalidadeEmissao = finalidadeEmissao;
	}

	public IdentificadorProcessoEmissao getIdentificadorProcessoEmissao() {
		return identificadorProcessoEmissao;
	}

	public void setIdentificadorProcessoEmissao(
			IdentificadorProcessoEmissao identificadorProcessoEmissao) {
		this.identificadorProcessoEmissao = identificadorProcessoEmissao;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public BigDecimal getValorNotaFiscal() {
		return valorNotaFiscal;
	}

	public void setValorNotaFiscal(BigDecimal valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}

	public String getChaveNfe() {
		return chaveNfe;
	}

	public void setChaveNfe(String chaveNfe) {
		this.chaveNfe = chaveNfe;
	}

	public String getDigestValue() {
		return digestValue;
	}

	public void setDigestValue(String digestValue) {
		this.digestValue = digestValue;
	}

	public Emitente getEmitente() {
		return emitente;
	}

	public void setEmitente(Emitente emitente) {
		this.emitente = emitente;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	public Arquivo getArquivoNotaFiscal() {
		return arquivoNotaFiscal;
	}

	public void setArquivoNotaFiscal(Arquivo arquivoNotaFiscal) {
		this.arquivoNotaFiscal = arquivoNotaFiscal;
	}

}
