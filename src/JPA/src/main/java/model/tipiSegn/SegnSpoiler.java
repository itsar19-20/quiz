package model.tipiSegn;
import model.Commento;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Entity implementation class for Entity: SegnSpoiler
 *
 */
@Entity
public class SegnSpoiler extends  Extended implements Serializable {

	private static final long serialVersionUID = 1L;

	public SegnSpoiler() {
		super("spoiler");
	}

	@ManyToOne
	@JoinColumn(name ="commento_id")

	private Commento comm;

	@JsonGetter 
	public Commento getComm() {
		return comm;
	}

    @JsonSetter
 	public void setComm(Commento comm) {
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "SegnSpoiler [getComm()=" + getComm() + ", getLavorazione()=" + getLavorazione() + ", getTipo()="
				+ getTipo() + ", getId()=" + getId() + ", getRisolutore()=" + getRisolutore() + ", getAutore()="
				+ getAutore() + ", getData()=" + getData() + ", isRisolta()=" + isRisolta() +  "]";
	}

	
	
	
	








}
