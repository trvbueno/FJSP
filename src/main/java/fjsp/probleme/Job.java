package fjsp.probleme;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Job {

    public ArrayList<Tache> taches;

    public Job()
    {
        this.taches = new ArrayList<Tache>();
    }

    public void ajouterTache(Tache t)
    {
        this.taches.add(t);
        t.parent = this;
    }

    public int tachesFlexibles()
    {
        int cpt = 0;
        for(Tache t: this.taches)
        {
            if(t.estFlexible())
                cpt++;
        }

        return cpt;
    }

    public Tache tacheFlexibleAleatoire()
    {
        int tf = this.tachesFlexibles();
        if(tf > 0)
        {
            int tf_sel = ThreadLocalRandom.current().nextInt(0, tf+1);
            int tf_idx = 0;

            for(Tache t: this.taches)
            {
                if(t.estFlexible())
                    tf_idx++;

                if(tf_sel == tf_idx)
                    return t;
            }
        }

        // TODO: gestion d'erreur avec exception
        return null;
    }
}
