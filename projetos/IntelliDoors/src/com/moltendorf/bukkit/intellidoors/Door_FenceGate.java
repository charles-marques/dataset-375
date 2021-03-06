package com.moltendorf.bukkit.intellidoors;

import org.bukkit.Material;

/**
 * Door class for Trap Doors.
 *
 * @author moltendorf
 */
class Door_FenceGate extends Door {
	// Constants.
	private static final List list = new List();
	private static final Material material = Material.FENCE_GATE;

	private Door_FenceGate(final Set set, final boolean open, final long current) {
		super (set, open, current);
	}

	private Door_FenceGate(final Set set, final boolean open) {
		super (set, open);
	}

	protected static void Destruct() {
		list.destruct();
	}

	protected static Door Get(final Set_FenceGate set, final boolean open) {
		final Door door = list.get(set);

		if (door == null) {
			return new Door_FenceGate(set, open).push();
		}

		return door;
	}

	@Override
	protected boolean apply(final boolean open) {
		return set.apply(open, material);
	}

	@Override
	protected boolean apply(final boolean side, final boolean open) {
		return set.apply(side, open, material);
	}

	@Override
	protected Door make(final Set set) {
		return new Door_FenceGate(set, open, time);
	}

	private Door push() {
		list.push(this);

		return this;
	}

	@Override
	public void run() {
		run(list);
	}

	@Override
	public void splice() {
		list.splice(this);
	}
}
