package objects;

import java.util.Comparator;

/**
 * Created by jarndt on 8/8/16.
 */
public class Coordinates implements Comparable<Coordinates>{
    String universe;
    int galaxy, system, planet, type;
    
    public static final int PLANET = 0, MOON = 1, DEBRIS_FIELD = 2;

    public Coordinates(String coordinates) {
        coordinates = coordinates.replace("[","").replace("]","");
        String[] split = coordinates.split(":");
        galaxy = Integer.parseInt(split[0]);
        system = Integer.parseInt(split[1]);
        planet = Integer.parseInt(split[2]);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                (universe == null? "" : "universe='" + universe + '\'') +
                ", [ " + galaxy +
                " : " + system +
                " : " + planet +
                " ] }";
    }

    @Override
    public int hashCode() {
        int result = galaxy;
        result = 31 * result + system;
        result = 31 * result + planet;
        return result;
    }

    public Coordinates(String universe, int galaxy, int system, int planet) {

        this.universe = universe;
        this.galaxy = galaxy;
        this.system = system;
        this.planet = planet;
    }

    public Coordinates(int galaxy, int system, int planet) {
        this.galaxy = galaxy;
        this.system = system;
        this.planet = planet;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public int getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(int galaxy) {
        this.galaxy = galaxy;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public int getPlanet() {
        return planet;
    }

    public void setPlanet(int planet) {
        this.planet = planet;
    }

    public String getCoordinates() {
        return galaxy+":"+system+":"+planet;
    }
    
    @Override
    public boolean equals(Object other){
    	if(other instanceof Coordinates){
    		Coordinates oc = (Coordinates)other;
    		if(universe != null && oc.universe != null && !universe.equals(oc.universe)){
    			return false;
    		}
    		return galaxy == oc.galaxy && planet == oc.planet && system == oc.system;
    	}
    	return false;
    
    }

	@Override
	public int compareTo(Coordinates o) {
		return galaxy*1000000-o.galaxy*1000000+system*20-o.system*20+planet-o.planet;
	}
	
	public int getDistance(Coordinates other){
		if(galaxy != other.galaxy){
			return getMinDistance(galaxy, other.galaxy, 9)*20000;
		}
		if(system != other.system){
			return 2700 + getMinDistance(system, other.system, 499)*95;
		}
		if(planet != other.planet){
			return 1000 + Math.abs(planet - other.planet)*5;
		}
		return 5;
	}
	
	public static int getTime(int distance, int speed){
		return 10 + (int)(3500*Math.sqrt(10*distance/speed));
	}
	
	public int getTime(Coordinates other, int speed){
		return Coordinates.getTime(getDistance(other), speed);
	}
	
	private int getMinDistance(int one, int two, int loop){
		int distance = Math.abs(one - two);
		return Math.min(distance, 499-loop);
	}
}
