public class NBody{
    public static double readRadius (String fileName){
        In in = new In(fileName);
        int n = in.readInt();
        double r = in.readDouble();
        return r;
    }
    public static Planet[] readPlanets (String fileName){
        
        In in = new In(fileName);

        int n = in.readInt();
        double r = in.readDouble();
        Planet[] planets = new Planet[n];
        
        for(int i = 0; i < n; i++){
            double x = in.readDouble();
            double y = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String planetFileName = in.readString();
            planets[i] = new Planet(x, y, vx, vy, m, planetFileName);
        }
        return planets;
    }

    public static void main(String[] args) {

        //Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        
        /** 
            Show the offscreen buffer (see the show method of StdDraw).
            Pause the animation for 10 milliseconds (see the pause method of StdDraw). You may need to tweak this on your computer.
            Increase your time variable by dt. */
        StdDraw.setScale(-r,r);
        StdDraw.enableDoubleBuffering();

        double time = 0;
        int len = planets.length;
        
        while(time < T){
            
            double[] xForces = new double[len];
            double[] yForces = new double[len];

            // Calculate the net x and y forces for each planet
            for(int i = 0; i < len; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            //Call update on each of the planets. 
            for(int i = 0; i < len; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            //Drawing the Background
            String background = "images/starfield.jpg";
            StdDraw.picture(0, 0, background);
            
            // Draw the planets
            for(Planet planet : planets){
                planet.draw();
            }

            StdDraw.show();    
            StdDraw.pause(10);	
            time += dt;
        }

        // Printing the Universe
        StdOut.printf("%d\n", len);
        StdOut.printf("%.2e\n", r);
        for(int i = 0; i < len; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName); 
        }
    }
    
}