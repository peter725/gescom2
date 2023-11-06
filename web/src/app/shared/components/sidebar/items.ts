import { MenuItem } from './model';


export const SIDEBAR_ITEMS: MenuItem[] = [
  {
    text: 'pages.home',
    path: 'home',
    icon: 'home',
  },
  {
    /*text: 'ARCHIVO',
    path: 'phone',
    icon: 'manage_accounts',
    expanded: false,
    children: [{
        text: 'Inicio',
        icon: 'phone',
        expanded: false,
        path:'arbitrationManagementList'
      },
      {
        text: 'Ayuda',
        icon: 'help',
        expanded: false,
        path:'arbitrationManagementList'
      },
      {
        text: 'Video Tutoriales',
        icon: 'play_circle',
        expanded: false,
        path:'arbitrationManagementList'
      },
      {
        text: 'Soporte Técnico',
        icon: 'support_agent',
        expanded: false,
        path:'arbitrationManagementList'
      },
      {
        text: 'Cerrar Sesión',
        icon: 'logout',
        expanded: false,
        path:'arbitrationManagementList'
      }
    ]
  },
  {
    text: 'MIS DATOS',
    // requireAccess?: ResourceAccessKey;
    icon: 'description',
    expanded:false,
    children: [{
      text: 'Ver mis datos',
      icon: 'visibility',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
      {
        text: 'Cambiar mi password',
        icon: 'password',
        // requireAccess?: ResourceAccessKey;
        expanded:false,
        path:'arbitrationManagementList'
      }]
  },
  {
    text: 'CAMPAÑAS',
    icon: 'work',
    expanded: false,
    children: [{
      text: 'Nueva campaña',
      icon: 'add',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Consultas',
      icon: 'lists',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Informes',
      icon: 'feed',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Enviar avisos',
      icon: 'send',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    }
    ]
  },
  {
    text: 'CONTROL SISTEMATICO',
    icon: 'work',
    expanded: false,
    children: [{
      text: 'Nuevo control',
      icon: 'add',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
      {
        text: 'Consultas',
        icon: 'lists',
        // requireAccess?: ResourceAccessKey;
        expanded:false,
        path:'arbitrationManagementCreate'
      },
      {
        text: 'Informes',
        icon: 'feed',
        // requireAccess?: ResourceAccessKey;
        expanded:false,
        path:'arbitrationManagementCreate'
      },
      {
        text: 'Enviar avisos',
        icon: 'send',
        // requireAccess?: ResourceAccessKey;
        expanded:false,
        path:'arbitrationManagementCreate'
      }
    ]
  },
  {
    text: 'PROPUESTAS',
    icon: 'work',
    expanded: false,
    children: [{
      text: 'Nueva',
      icon: 'add',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Mis propuestas',
      icon: 'lists',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Generar listados',
      icon: 'feed',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    }
    ]
  },
  {
    text: 'CONTROL ANALÍTICO',
    icon: 'work',
    expanded: false,
    children: [{
      text: 'Nueva',
      icon: 'add',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Consulta',
      icon: 'lists',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    }
    ]
  },
  {
    text: 'DOCUMENTOS',
    icon: 'folder',
    expanded: false,
    children: [{
      text: 'Modelos',
      icon: 'description',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    },
    {
      text: 'Subir documentos',
      icon: 'upload_file',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    }
    ]
  },
  {*/
    text: 'pages.user.title',
    icon: 'person',
    expanded: false,
    children: [{
      text: 'pages.user.add', path:'userManagementCreate' },
    { text: 'pages.user.list', path:'userManagementList' }
    ]
  }/*,
  {
    text: 'ENLACES',
    icon: 'link',
    expanded: false,
    children: [{
      text: 'Colabora',
      icon: 'exit_to_app',
      // requireAccess?: ResourceAccessKey;
      expanded:false,
      path:'arbitrationManagementCreate'
    }
    ]
  }*/

];
