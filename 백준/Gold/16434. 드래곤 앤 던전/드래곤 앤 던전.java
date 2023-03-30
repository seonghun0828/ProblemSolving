import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static class Room {
		boolean hasMonster;
		int atk, hp;

		public Room(boolean hasMonster, int atk, int hp) {
			this.hasMonster = hasMonster;
			this.atk = atk;
			this.hp = hp;
		}
	}

	static int N;
	static long currAtk;
	static Room[] rooms;

	public static void main(String[] args) throws IOException {
		inputs();

		/**
		 * maxHP를 1로 시작해서 몬스터가 용사를 죽이는 경우에 그 차이 + 1만큼을 더해 용사를 살려줌. 그 차이를 계속해서 더해가며 초기
		 * maxHP를 구함.
		 */

		long maxHP = 1;
		long currHP = 1;

		for (Room next : rooms) {
			if (next.hasMonster) { // 다음 방에 몬스터가 있으면
				long atkCnt = ceil(next.hp, currAtk); // 몬스터를 쓰러뜨리기 위해 공격하는 횟수
				long monsterAtk = (atkCnt - 1) * next.atk; // 몬스터가 용사에게 공격하는 공격력의 총합
				if (currHP <= monsterAtk) { // currHP보다 몬스터의 공격 총합이 많거나 같으면
					long diff = monsterAtk - currHP + 1;
					currHP += diff; // 그 차이 + 1만큼 currHP와 maxHP에 더해줌
					maxHP += diff;
				}
				currHP -= monsterAtk; // 몬스터의 공격으로 currHP 깎임
			} else { // 다음 방에 포션이 있으면
				currHP = currHP + next.hp >= maxHP ? maxHP : currHP + next.hp;
				currAtk += next.atk;
			}
		}
		System.out.println(maxHP);
	}

	private static long ceil(int hp, long atk) {
		return hp % atk == 0 ? (hp / atk) : (hp / atk) + 1;
	}

	private static void inputs() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		currAtk = Long.parseLong(st.nextToken());
		rooms = new Room[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			rooms[i] = new Room(st.nextToken().equals("1"), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
	}
}