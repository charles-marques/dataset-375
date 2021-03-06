package net.minecraft.src;

import java.util.List;
import java.util.Random;

public abstract class ThxEntityProjectile extends ThxEntity
{
    boolean enteredWater;
    boolean launched;
    
	public ThxEntityProjectile(World world)
    {
        super(world);
        
        helper = createHelper();
        
        //setSize(0.25F, 0.25F);
        setSize(.5f, .5f);
    }

    public ThxEntityProjectile(Entity owner, double x, double y, double z, double dx, double dy, double dz, float yaw, float pitch)
    {
        this(owner.worldObj);
        
        this.owner = owner;
        
        setPositionAndRotation(x, y, z, yaw, pitch);
        
        float acceleration = getAcceleration();
        
        updateRotation();
        updateVectors();
        
        motionX = fwd.x * acceleration + dx;
        motionY = fwd.y * acceleration + dy;
        motionZ = fwd.z * acceleration + dz;
        
        isImmuneToFire();
    }

    public void onUpdate()
    {
        super.onUpdate();
        
        if (owner == null) // should always have owner, but sometimes doesn't during respawn/restart etc
        {
            setDead();
            return;
        }
	        
        if (ticksExisted > 300) // 15 second lifespan
        {
            detonate();
            return;
        }
	        
        if (!launched)
        {
            launched = true;
            onLaunch();
        }
        
        createParticles();
        
        Vec3D posStart = Vec3D.createVector(posX, posY, posZ);
        Vec3D posEnd = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(posStart, posEnd);
        if(movingobjectposition != null)
        {
            posEnd = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        
        setPosition(posEnd.xCoord, posEnd.yCoord, posEnd.zCoord);
        
        if(!enteredWater && isInWater())
        {
            enteredWater = true;
            
            motionX *= .7f;
            motionY *= .7f;
            motionZ *= .7f;
            
            worldObj.playSoundAtEntity(this, "random.splash", 1f, 1f);
            for(int l = 0; l < 4; l++)
            {
                float f3 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f3, posY - motionY * (double)f3, posZ - motionZ * (double)f3, motionX, motionY, motionZ);
            }
        }
        
        // check for nearby entities
        Entity entity = null;
        double closest = 1.0;
        
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 1.0D, 1.0D));
        for (int i = 0; i < list.size(); i++)
        {
            Entity nextEntity = (Entity) list.get(i);
            if (nextEntity == null) continue;
            if (!nextEntity.canBeCollidedWith()) continue;
            
            if (nextEntity.equals(owner)) continue;
            
            if (owner == null) log(this + " owner is null");
            
            if (owner.equals(nextEntity.riddenByEntity) || owner.equals(nextEntity))
            {
                log("skipping self");
                continue;
            }
            
            float f4 = 0.3F;
            AxisAlignedBB axisalignedbb = nextEntity.boundingBox.expand(f4, f4, f4);
            MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(posStart, posEnd);
            
            if (movingobjectposition1 == null) continue;
            
            double distanceToEntity = posStart.distanceTo(movingobjectposition1.hitVec);
            if (distanceToEntity < closest)
            {
                entity = nextEntity; // remember closest entity
                closest = distanceToEntity;
            }
        }

        if (entity != null)
        {
            // we hit an entity!
            movingobjectposition = new MovingObjectPosition(entity);
            
            if (owner == null || owner.equals(entity.riddenByEntity) || owner.equals(entity))
            {
                log(owner + " ignoring hit from own rocket");
            }
            else
            {
                strikeEntity(entity);
	        
		        detonate();
	            return;
            }
        }
        
        if (movingobjectposition != null) // we hit something besides an entity
        {
	        int i = MathHelper.floor_double(posX);
	        int j = MathHelper.floor_double(posY - 0.2 -(double)yOffset);
	        int k = MathHelper.floor_double(posZ);
	        int blockId = worldObj.getBlockId(i, j, k);
	        if (blockId > 0)
	        {
		        // kick up some debris if we hit a block, but only works for top surface
	            for (int k1 = 0; k1 < 4; k1++)
	            {
		            worldObj.spawnParticle((new StringBuilder()).append("tilecrack_").append(blockId).toString(), posX + ((double)rand.nextFloat() - 0.5) * (double)width, boundingBox.minY + 0.1, posZ + ((double)rand.nextFloat() - 0.5) * (double)width, 1.0 + ((double)rand.nextFloat() - 0.5),  1.0 + ((double)rand.nextFloat() - 0.5),  1.0 + ((double)rand.nextFloat() - 0.5));
	            }
	        }
	        else
	        {
	            // create some non-block debris instead
	            for (int k1 = 0; k1 < 4; k1++)
	            {
		            worldObj.spawnParticle("snowballpoof", posX + ((double)rand.nextFloat() - 0.5), boundingBox.minY + 0.1, posZ + ((double)rand.nextFloat() - 0.5),  1.0 + ((double)rand.nextFloat() - 0.5),  1.0 + ((double)rand.nextFloat() - 0.5),  1.0 + ((double)rand.nextFloat() - 0.5));
	            }
	        }
	        
	        detonate();
            return;
        }
    }
    
    void doSplashDamage(double splashSize, int damage)
    {
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(splashSize, splashSize, splashSize));
        for (int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity) list.get(i);
            if (!entity.canBeCollidedWith()) continue;
            
	        entity.attackEntityFrom(new EntityDamageSource("projectile splash damage", owner), damage); // splash damage is same as rocket hit
        }
    }
    
    abstract float getAcceleration();
    
    abstract void onLaunch();
    
    abstract void createParticles();
    
    abstract void strikeEntity(Entity entity);
    
    abstract void detonate();
}

