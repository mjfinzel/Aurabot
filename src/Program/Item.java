package Program;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class Item {
	int rarity=-1;
	int baseType=-1;
	double physDmg = 0;
	double eleDmg = 0;
	double chaosDmg = 0;
	double dps=0;
	double pdps=0;
	double edps=0;
	double attackSpeed = 0;
	boolean identified = false;
	int sockets = 0;
	int links = 0;
	double quality = 0;
	String crafted = "";

	ArrayList<String> affixes = new ArrayList<String>();
	ArrayList<String> prefixes = new ArrayList<String>();
	ArrayList<String> suffixes = new ArrayList<String>();

	int life = 0;
	int fireResist = 0;
	int coldResist = 0;
	int lightningResist = 0;
	int chaosResist = 0;
	int dexterity = 0;
	int strength = 0;
	int intelligence = 0;
	int lightRadius = 0;
	int accuracy = 0;
	int movespeed=0;
	int energyShield = 0;
	int increasedRarity = 0;
	int spellDmg=0;

	public Item() {

	}
	public void removePrefix(String s) {
		for(int i = 0; i<prefixes.size();i++) {
			if(prefixes.get(i).contains(s)) {
				prefixes.remove(i);
				return;
			}
		}
	}
	public void removeSuffix(String s) {
		for(int i = 0; i<suffixes.size();i++) {
			if(suffixes.get(i).contains(s)) {
				suffixes.remove(i);
				return;
			}
		}
	}
	public String hasAffix(String s) {
		for(int i = 0; i<affixes.size();i++)
			if(affixes.get(i).contains(s))
				return affixes.get(i);
		return null;
	}
	public String hasPrefix(String s) {
		for(int i = 0; i<prefixes.size();i++)
			if(prefixes.get(i).contains(s))
				return prefixes.get(i);
		return null;
	}
	public String hasPrefix(String s1, String s2) {
		for(int i = 0; i<prefixes.size();i++)
			if(prefixes.get(i).contains(s1)&&prefixes.get(i).contains(s2))
				return prefixes.get(i);
		return null;
	}
	public String hasSuffix(String s) {
		for(int i = 0; i<suffixes.size();i++)
			if(suffixes.get(i).contains(s))
				return suffixes.get(i);
		return null;
	}
	public int totalResist() {
		int result = 0;
		String fire = hasAffix("Fire Resistance");
		String lightning = hasAffix("Lightning Resistance");
		String cold = hasAffix("Cold Resistance");
		String chaos = hasAffix("Chaos Resistance");
		if(fire!=null) result+=Integer.valueOf(fire.substring(1, fire.length()-"% to Fire Resistance".length()));
		if(lightning!=null) result+=Integer.valueOf(lightning.substring(1, lightning.length()-"% to Lightning Resistance".length()));
		if(cold!=null) result+=Integer.valueOf(cold.substring(1, cold.length()-"% to Cold Resistance".length()));
		if(chaos!=null) result+=Integer.valueOf(chaos.substring(1, chaos.length()-"% to Chaos Resistance".length()));
		return result;
	}
	public void Draw(Graphics g) {
		Font font = new Font("Iwona Heavy",Font.PLAIN,12);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();
		int count = 0;
		
		g.drawString("Crafted:", 500, 25+(count*12));
		if(rarity!=-1) g.drawString(crafted+"", 600, 25+(count*12)); count++;
		
		g.drawString("Rarity:", 500, 25+(count*12));
		if(rarity!=-1) g.drawString(ItemInfo.names[rarity], 600, 25+(count*12)); count++;

		g.drawString("Base Type:", 500, 25+(count*12));
		if(baseType!=-1) g.drawString(ItemInfo.names[baseType], 600, 25+(count*12)); count++;

		g.drawString("DPS:", 500, 25+(count*12));
		g.drawString((int)dps+"", 600, 25+(count*12)); count++;

		g.drawString("pDPS:", 500, 25+(count*12));
		g.drawString((int)pdps+"", 600, 25+(count*12)); count++;

		g.drawString("eDPS:", 500, 25+(count*12));
		g.drawString((int)edps+"", 600, 25+(count*12)); count++;

		g.drawString("Sockets:", 500, 25+(count*12));
		g.drawString(sockets+"", 600, 25+(count*12)); count++;

		g.drawString("Links:", 500, 25+(count*12));
		g.drawString((int)links+"", 600, 25+(count*12)); count++;

		g.drawString("Quality:", 500, 25+(count*12));
		g.drawString((int)quality+"", 600, 25+(count*12)); count++;
		
		g.drawString("Affixes:", 500, 300);
		for(int i = 0; i<affixes.size();i++) {
			g.drawString(affixes.get(i), 550, 320+(i*12));
		}
	}
}

