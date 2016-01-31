package comunicaComu;

public class SPlayer {
public String nom;
public Estats estat;
public String pass;

public SPlayer(){}

public SPlayer(String nom, Estats estat){
	this.nom = nom;
	this.estat = estat;
	}
public SPlayer(String nom){
	this.nom = nom;
}
public SPlayer (String nom,String pass){
	this.nom = nom;
	this.pass = pass;
}
}
