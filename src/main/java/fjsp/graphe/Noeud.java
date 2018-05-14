package fjsp.graphe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import fjsp.probleme.*;

public class Noeud {

    ArrayList<Arc> contraintes;

    public Tache tache;

    public Noeud(Tache t)
    {
        this.tache = t;
        this.contraintes = new ArrayList<Arc>();
    }

    public void contraindre(Noeud precedent, int cout)
    {
        Arc a = new Arc(precedent, this, cout);
        contraintes.add(a);
    }

    public int coutMax()
    {
        int cout_max = 0;

        for(Arc a: contraintes)
        {
            int coucou = a.cout + a.pred.coutMax();
            if(cout_max < coucou)
                cout_max = coucou;
        }

        return cout_max;
    }

    public void afficherDot()
    {
        this.afficherDot(1, null);
    }

    private void afficherDot(int choix, ArrayList<Noeud> visites)
    {
        if(visites == null)
            visites = new ArrayList<Noeud>();
        else if(visites.contains(this))
            return;

        visites.add(this);

        if (choix == 1)
            System.out.println("digraph S {");

        for (Arc a: contraintes)
        {
            a.afficherArc();
            a.pred.afficherDot(0, visites);
        }

        if (choix == 1)
            System.out.println("}");
    }
}
