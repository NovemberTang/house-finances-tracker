package financetracker

import financetracker.SplitMethod.SplitMethod

class Updater(val name: String, val role: String, val balance: Double, val split: SplitMethod){
  override def toString = s"$name, $role, $balance"
}
