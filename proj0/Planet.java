public class Planet{
    public double xxPos; //Its current x position
    public double yyPos; //Its current y position
    public double xxVel; //Its current velocity in the x direction
    public double yyVel; //Its current velocity in the y direction
    public double mass; //Its mass
    public String imgFileName; //The name of the file that corresponds to the image that depicts the planet
    private static final double G = 6.67e-11;
    // constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    // The second constructor: copy
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    // calculates the distance between two Planets
    public double calcDistance(Planet p ){
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }
    // calculates the force exerted on this planet by the given planet
    public double calcForceExertedBy(Planet p){
        double r2 = this.calcDistance(p) * this.calcDistance(p);
        double f = G * this.mass * p.mass / r2;
        return f;
    }
    // calculates the force exerted in the X direction
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        double fx = this.calcForceExertedBy(p) * dx / r;
        return fx;
    }
    // calculates the force exerted in the Y direction
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        double fy = this.calcForceExertedBy(p) * dy / r;
        return fy;
    }
    // calculate the net X force exerted by all planets in that array
    public double calcNetForceExertedByX(Planet[] all){
        double fnx = 0;
        for(int i = 0; i < all.length; i++){
            if(!this.equals(all[i])){
                fnx += this.calcForceExertedByX(all[i]);
            }
        }
        return fnx;
    }
    // calculate the net Y force exerted by all planets in that array
    public double calcNetForceExertedByY(Planet[] all){
        double fny = 0;
        for(int i = 0; i < all.length; i++){
            if(!this.equals(all[i])){
                fny += this.calcForceExertedByY(all[i]);
            }
        }
        return fny;
    }
    // update the planet’s position and velocity instance variables
    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    // draw the planet
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
        
    }
}