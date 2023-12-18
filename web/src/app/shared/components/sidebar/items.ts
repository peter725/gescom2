import { MenuItem } from './model';


export const SIDEBAR_ITEMS: MenuItem[] = [
  // Inicio
  {
    text: 'pages.home',
    path: 'home',
    icon: 'home',
  },
  // Propuestas
  {
    text: 'pages.approach.title', 
    icon: 'work',
    expanded: false,
    children: [{
      text: 'pages.approach.add', path:'approachManagementCreate'
    },
    {
      text: 'pages.approach.list', path:'approachManagementList'
    }
    ]
  },
  // Campañas
  {
    text: 'pages.sampleSeason.title',
    icon: 'campaign',
    expanded: false,
    children: [{
      text: 'pages.sampleSeason.add', path:'sampleSeasonCreate'
    },
    {
      text: 'pages.sampleSeason.list', path:'sampleSeasonList'
    }
    ]
  },
  // Usuarios
  {
    text: 'pages.user.title',
    icon: 'person',
    expanded: false,
    children: [{
      text: 'pages.user.add', path:'userManagementCreate' },
    { text: 'pages.user.list', path:'userManagementList' }
    ]
  }

];
