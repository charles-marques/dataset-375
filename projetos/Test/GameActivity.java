package projet.jeu.bille;
import java.io.InputStream;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.widget.Chronometer;

public class GameActivity extends Activity{

    private final static int ID_NORMAL_DIALOG = 0;
    private final static int ID_ENERVEE_DIALOG = 1;

    //Sounds
    MediaPlayer soundCollision,soundLevel1;//,soundLevel2,soundLevel3,soundLevel4,soundLevel5,soundLevel6,soundLevel7;
    InputStream level1;//,level2,level3,level4,level5,level6,level7;
   
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    

        int largeur = getWindowManager().getDefaultDisplay().getWidth()/ViewSpace.UNITE;
        int hauteur = getWindowManager().getDefaultDisplay().getHeight()/ViewSpace.UNITE;

	//    initiateSounds();
    //    initiateLevels();
        Sounds sounds = new Sounds(this);
        Levels levels = new Levels(this);
        Chronometer chronometer = new Chronometer(this); 
	 //   Sounds sounds = new Sounds(soundCollision,soundLevel1,soundLevel2,soundLevel3,soundLevel4,soundLevel5,soundLevel6,soundLevel7);
     //   Levels levels = new Levels(level1,level2,level3,level4,level5,level6,level7);
    //    Sounds sounds = new Sounds(soundCollision,soundLevel1);
    //    Levels levels = new Levels(level1);
        Game game = new Game(sounds, levels,chronometer,largeur, hauteur);

	    ViewSpace runnerView = new ViewSpace(this,game);
        runnerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        setContentView(runnerView);
        runnerView.startIt();
	}
    	

	@Override
	public Dialog onCreateDialog(int identifiant) {
		  Dialog box = null;
	        switch(identifiant) {
		    // Quand on appelle avec l'identifiant de la boîte normale
		    case ID_NORMAL_DIALOG:
		        box = new Dialog(this);
		        box.setTitle("Je viens tout juste de naitre.");
		        break;
				
		    // Quand on appelle avec l'identifiant de la boîte qui s'énerve
		    case ID_ENERVEE_DIALOG:
		        box = new Dialog(this);
		        box.setTitle("Tu as perdu !!");
	        }
	        return box;
	}
/*	 
    public void lancerAnimation() {
     //   RunGame r = new RunGame(this);
        thread = new Thread(this);
        thread.start();
}*/
	/*
	@Override
	public Dialog onCreateDialog(int id) {
	    AlertDialog retour = null;
	    AlertDialog.Builder builder = null;
	    LayoutInflater inflater = getLayoutInflater();

	    switch(id) {
	    	case DIALOG_ADD:
	    	    final RelativeLayout layoutAdd = (RelativeLayout) inflater.inflate(R.layout.addmodifydialog, null);
	    	    builder = new AlertDialog.Builder(this)
	    			.setView(layoutAdd)
	    			.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Contact c = new Contact();
							//Récupérer les champs
							EditText nom = (EditText)layoutAdd.findViewById(R.edit.name);
							EditText phone = (EditText)layoutAdd.findViewById(R.edit.phone);
							ToggleButton gender = (ToggleButton)layoutAdd.findViewById(R.bouton.gender);
							
							//On met les informations dans le nouveau contact
							c.setNom(nom.getText().toString().trim());
							c.setNumero(phone.getText().toString().trim());
							c.setHomme(gender.isChecked());
							//Si on avait pas d'adaptateur, on le crée
							if(mAdapter == null)
								mAdapter = new RepertoireAdapter();
							//On ajoute l'item et si la liste n'avait pas encore d'adaptateur, on lui attribut
							if(mAdapter.addItem(c) && getListAdapter() == null)
									setListAdapter(mAdapter);
						}
					})
					.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Fermer la boîte de dialogue
							dismissDialog(DIALOG_ADD);
						}
					})
					.setTitle("Ajouter un nouvel utilisateur");
	    	    break;
	    	case DIALOG_MULTIPLE_DELETE:
	    	    builder = new AlertDialog.Builder(this)
	    			.setTitle("Supprimer ces utilisateurs ?")
	    			.setMessage("Êtes-vous certain de vouloir supprimer ces utilisateurs ?")
	    			.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Récupération des contacts sélectionnés
							ArrayList<Integer> selected = mAdapter.getListeSelected();
							int position;
							//On parcourt en sens inverse la liste...
							for(int i = selected.size() ; i > 0 ; i--)
							{
								//position du ième contact sélectionné
								position = selected.get(i - 1);
								//et on supprime le contact à cette position
								mAdapter.deleteItem(position);
							}
						}
					});
	    	    break;
	    	case DIALOG_MODIFY:
	    	    final RelativeLayout layoutModifiy = (RelativeLayout) inflater.inflate(R.layout.addmodifydialog, null);
	    	    builder = new AlertDialog.Builder(this)
					.setView(layoutModifiy)
					.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Contact c = new Contact();
							//Récupération des informations insérées dans les champs
							EditText nom = (EditText)layoutModifiy.findViewById(R.edit.name);
							EditText phone = (EditText)layoutModifiy.findViewById(R.edit.phone);
							ToggleButton gender = (ToggleButton)layoutModifiy.findViewById(R.bouton.gender);
							
							//On le insère dans le contact
							c.setNom(nom.getText().toString().trim());
							c.setNumero(phone.getText().toString().trim());
							c.setHomme(gender.isChecked());
							//Et remplacement du contact déjà existant
							mAdapter.modifyItem(c, currentContact.hashCode());
						}
					})
					.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//Fermer la boîte de dialogue
							dismissDialog(DIALOG_MODIFY);
						}
					})
					.setTitle("Modifier un utilisateur");
	    	    break;	
	    }
	    retour = builder.create();
	    return retour;
	}
	    
	@Override
	public void onPrepareDialog (int id, Dialog dialog) {
	    switch(id) {
	    	case DIALOG_ADD:
		    EditText nom = (EditText)dialog.findViewById(R.edit.name);
		    EditText phone = (EditText)dialog.findViewById(R.edit.phone);
		    ToggleButton gender = (ToggleButton)dialog.findViewById(R.bouton.gender);
				
		    // Remise à zéro des champs de la boîte de dialogue
		    nom.setText("");
		    // On accorde le focus au premier champ
		    nom.requestFocus();
		    phone.setText("");
		    gender.setChecked(false);
		    break;

	    	case DIALOG_MODIFY:
	    		EditText nomMod = (EditText)dialog.findViewById(R.edit.name);
		    EditText phoneMod = (EditText)dialog.findViewById(R.edit.phone);
		    ToggleButton genderMod = (ToggleButton)dialog.findViewById(R.bouton.gender);
				
		    // Les champs de la boîte sont complétés avec les informations sur le contadt
		    nomMod.setText(currentContact.getNom());
		    // On accorde le focus au premier champ
		    nomMod.requestFocus();
		    phoneMod.setText(currentContact.getNumero());
		    genderMod.setChecked(currentContact.isHomme());
		    break;
	    	}
	    }
	    */    
    }