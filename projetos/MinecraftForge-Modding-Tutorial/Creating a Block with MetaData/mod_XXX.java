package net.minecraft.src.tutorial.metablock;

import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.MinecraftForgeClient;

//each mod needs to have a class with the name mod_ infront of the name
public class mod_XXX extends BaseMod {
	
	public static Block blockXXX; // Our block we want to create with a recipe
	public static Item itemXXX; // our item we want to create with a recipe
	
	public static final String itemPNG = "/tutorialtextures/items.png";
	public static final String blockPNG = "/tutorialtextures/blocks.png";
	
	@Override
	public String getVersion() {
		return "0.0.1"; // returns the version number as String
	}

	@Override
	public void load() {
		MinecraftForgeClient.preloadTexture(this.blockPNG);
		MinecraftForgeClient.preloadTexture(this.itemPNG);
		this.registerBlocks(); //register all blocks
		this.addNames(); //adding all names
		this.registerRecipes(); //register all recipes
	}
	
	//private method for proper source
	private void registerBlocks() {
		//ModLoader needs to know that you want to add a block to the game
		// to register a metadata block we need to call this function with the itemblock class as 2nd parameter
		ModLoader.registerBlock(blockXXX, net.minecraft.src.tutorial.itemblockXXX.class);
	}
	
	//private method for proper source
	private void addNames(){
		//this have to be called so modloader knows the itemname and can add it to minecraft
		
		//here we set the "blockname" to the name we want
		//we set the names in the itemblockXXX.java already to a id this id we use to set the itemname
		//with the additions of .name to tell modloader that this is a name 
		ModLoader.addLocalization("blockXXX.name", "Block Log 1"); 
		ModLoader.addLocalization("blockXXX2.name", "Block Log 2");
	}
	
	private void registerRecipes(){
		
		// Adding a Shaped recipe for our item
		// we get 2 itemXXX from this recipe
		ModLoader.addRecipe(new ItemStack(itemXXX, 2), new Object[] 
		{
			//the first 3 strings are your recipe in the craftingtable
			//the char 'X' is set to be a Item.Stick
			//and the char '+' ist set to be Block.Cobblestone
			//so our item will me maked out of 6 cobblestone and 1 stick
			"X X", "X+X", "X X", 'X', Item.stick, '+', Block.cobblestone
		});
		
		// Now we adding a Shapelessrecipe for our Block
		// we get 16 blockXXX from this recipe while we just need 3 itemXXX in any shape we put it in the crafting
		// first with metadata = 0, default if no given
		ModLoader.addShapelessRecipe(new ItemStack(blockXXX, 16), new Object[] {itemXXX, itemXXX, itemXXX});
		// next one with metadata = 1, the 2nd int parameter in new ItemStack
		ModLoader.addShapelessRecipe(new ItemStack(blockXXX, 16, 1), new Object[] {itemXXX, itemXXX});
	}
	
	static {
		Props.initProps("XXX"); //Init the Config with the Name XXX
		
		//new blockXXX  | Getting the blockID from the config 
		//setBlockName  | sets the blockName will be overwritten by modloader
		blockXXX = new blockXXX(Props.bXXXID, 0).setBlockName("Block XXX");
		itemXXX = new itemXXX(Props.iXXXID).setIconCoord(0, 0);
	}
}
