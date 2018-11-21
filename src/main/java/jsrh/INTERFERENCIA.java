package jsrh;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[GisDB].[dbo].[temp2interferencia]") //"[GisDB].[gisadmin].[INTERFERENCIA]")
public class INTERFERENCIA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) //strategy=GenerationType.IDENTITY
	@Column (name="ID_INTERFERENCIA")
	private int idInterferencia;
	
	@Column (name="ID_EMPREENDIMENTO") 
	private int idEmpreendimento;
	
	@Column (name="ID_UH") 
	private int idUH;
	
	

	public INTERFERENCIA () {
		
	}

	public INTERFERENCIA( 
			
			int idInterferencia2, 
			int idEmpreendimento2,
			int idUH
			) {
		
	}

	public int getIdInterferencia() {
		return idInterferencia;
	}

	public void setIdInterferencia(int idInterferencia) {
		this.idInterferencia = idInterferencia;
	}

	public int getIdEmpreendimento() {
		return idEmpreendimento;
	}

	public void setIdEmpreendimento(int idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}
	
	
	public int getIdUH() {
		return idUH;
	}

	public void setIdUH(int idUH) {
		this.idUH = idUH;
	}
	
	
	
	//temp2interferencia
	
	/*
	SELECT TOP 1000 [ID_INTERFERENCIA]
		      ,[ID_EMPREENDIMENTO]
		      ,[ID_UH]
		      ,[ID_TIPO_INTERFERENCIA]
		      ,[ID_TIPO_OUTORGA]
		      ,[ID_TIPO_ATO]
		      ,[ID_SITUACAO]
		      ,[NUM_PROCESSO]
		      ,[NUM_ATO]
		      ,[VERIFICADO]
		      ,[LATITUDE]
		      ,[LONGITUDE]
		      ,[SHAPE]
		      ,[DT_PUBLICACAO]
		      ,[DT_VENCIMENTO]
		      ,[OBSERVACAO]
		      ,[ID_INTERF_ANTIGO]
		      ,[NOME]
		  FROM [GisDB].[dbo].[temp2interferencia]
		  
		  */
}
