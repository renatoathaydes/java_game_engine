import game2d.*;

import java.util.Date;
import java.lang.Math;

public class Board
{
  public int map[][];

  // [4][8 * 16]
  private int mask[][] =
  {
    {
      0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
      0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
      0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0,
      0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
      0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
      0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0,
      0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
      0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0
    },
    {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    },
    {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    },
    {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    }
  };

  Board()
  {
    map = new int[4][8 * 16];

    int x, y;
  }

  public void reset()
  {
    /*
    0-8 = bamboo
    9-17 = circles
    18-26 = characters
    27-30 = flowers
    31-34 = winds
    35-38 = seasons
    39-41 = dragons 

    36 bamboos
    36 circles
    36 characters
    4 flowers
    16 winds
    4 seasons
    12 dragons
    */

    int tmap[] =
    {
      4, 4, 4, 4, 4, 4, 4, 4, 4,
      4, 4, 4, 4, 4, 4, 4, 4, 4,
      4, 4, 4, 4, 4, 4, 4, 4, 4,
      2, 2, 2, 2,
      2, 2, 2, 2,
      2, 2, 2, 2,
      4, 4, 4
    };

    int x, y, z;

    // make sure we get a decent random number
    //int ms = (int)(new Date().getTime());
    //Rnd.set(ms & 8191);

    int count = 0;
    for(z = 0; z < 4; z++)
    {
      for(y = 0; y < 8; y++)
      {
        for(x = 0; x < 16; x++)
        {
          if(getMask(x, y, z) > 0)
          {
            while(true)
            {
              int c = (int)(Math.random() * 42) % 42;
              //int c = Rnd.get() % 42;
              if(tmap[c] > 0)
              {
                setMap(x, y, z, c);
                tmap[c]--;
                count++;
                break;
              }
            }
          }
          else
          {
            setMap(x, y, z, -1);
          }
        }
      }
    }
  }

  public int getMap(int x, int y, int z)
  {
    if(x > 15 || x < 0 || y > 7 || y < 0)
      return -1;
    else
      return map[z][x + (y << 4)];
  }

  public void setMap(int x, int y, int z, int num)
  {
    if(x > 15 || x < 0 || y > 7 || y < 0)
      return;

    map[z][x + (y << 4)] = num;
  }
    
  public int getMask(int x, int y, int z)
  {
    if(x > 15 || x < 0 || y > 7 || y < 0)
      return 0;
    else
      return mask[z][x + (y << 4)];
  }

  public void setMask(int x, int y, int z, int num)
  {
    if(x > 15 || x < 0 || y > 7 || y < 0)
      return;

    mask[z][x + (y << 4)] = num;
  }

}

