package dev.ammar.ezspawn.WarpSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

public class Warp {
    private String name;
    private double x, y, z;
    private float yaw, pitch;
    private String owner;
    private String world;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getOwner(){return owner;}
    public void setOwner(String owner){this.owner = owner;}

    public double getX(){return x;}
    public void setX(double x){this.x = x;}

    public double getY(){return y;}
    public void setY(double y){this.y = y;}

    public double getZ(){return z;}
    public void setZ(double z){this.z = z;}

    public float getYaw(){return yaw;}
    public void setYaw(float yaw){this.yaw = yaw;}

    public float getPitch(){return pitch;}
    public void setPitch(float pitch){this.pitch = pitch;}

    public String getWorld(){return world;}
    public void setWorld(String world){this.world = world;}

    public Warp(){}

    public Warp(String name, String owner, String world, double x, double y, double z, float yaw, float pitch){
        this.owner = owner;
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Warp(Warp other){
        this.name = other.name;
        this.owner = other.owner;
        this.world = other.world;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.yaw = other.yaw;
        this.pitch = other.pitch;
    }

    public Location getLocation(){
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.AQUA).append(this.name)
                .append(ChatColor.WHITE)
                .append("<").append(this.world).append("> [")
                .append(this.x).append(", ")
                .append(this.y).append(", ")
                .append(this.z).append(", ")
                .append(this.yaw).append(", ")
                .append(this.pitch).append("]");
        return sb.toString();
    }
}
